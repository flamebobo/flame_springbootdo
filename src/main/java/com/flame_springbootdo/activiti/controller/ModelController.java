package com.flame_springbootdo.activiti.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.flame_springbootdo.common.controller.BaseController;

import com.flame_springbootdo.common.rabbitmq.RabbitSend;
import com.flame_springbootdo.common.utils.PageUtils;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.activiti.rest.editor.model.ModelEditorJsonRestResource;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.activiti.editor.constants.ModelDataJsonConstants.MODEL_DESCRIPTION;
import static org.activiti.editor.constants.ModelDataJsonConstants.MODEL_ID;
import static org.activiti.editor.constants.ModelDataJsonConstants.MODEL_NAME;
import static org.activiti.editor.constants.ModelDataJsonConstants.MODEL_REVISION;

/**
 * @Author Flame
 * @Date 2018/10/29 16:03
 * @Version 1.0
 */
@RequestMapping("/activiti")
@RestController
public class ModelController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ModelEditorJsonRestResource.class);

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RabbitSend rabbitSend;

    @GetMapping("/model")
    ModelAndView model() {
        return new ModelAndView("act/model/model");
    }

    @GetMapping("/model/list")
    public PageUtils list(int offset, int limit) {
        List<Model> list = repositoryService.createModelQuery().listPage(offset, limit);
        int total = (int) repositoryService.createModelQuery().count();
        PageUtils pageUtil = new PageUtils(list, total);
        return pageUtil;
    }

    @GetMapping("/model/edit/{id}")
    public void edit(HttpServletResponse response, @PathVariable("id") String id) {
        try {
            response.sendRedirect("/modeler.html?modelId=" + id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/model/{modelId}/json")
    public ObjectNode getEditorJson(@PathVariable String modelId) {
        ObjectNode modelNode = null;
        Model model = repositoryService.getModel(modelId);
        if (model!=null) {
            try {
                if (StringUtils.isNotEmpty(model.getMetaInfo())) {
                    modelNode = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
                } else {
                    modelNode = objectMapper.createObjectNode();
                    modelNode.put(MODEL_NAME, model.getName());
                }
                modelNode.put(MODEL_ID, model.getId());
                ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(
                        new String(repositoryService.getModelEditorSource(model.getId()),"UTF-8"));
                modelNode.put("model",editorJsonNode);
            } catch (IOException e) {
                logger.info("创建model JSON 失败 ", e);
                throw new ActivitiException("创建model JSON 失败 ", e);
            }
        }
        return modelNode;
    }

    @RequestMapping(value = "/editor/stencilset", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getStencilset(){
        InputStream stencilsetStream = this.getClass().getClassLoader().getResourceAsStream("stencilset.json");
        try {
            return IOUtils.toString(stencilsetStream, "utf-8");
        } catch (Exception e) {
            throw new ActivitiException("Error while loading stencil set", e);
        }
    }

    @RequestMapping("/model/add")
    public void newModel(HttpServletResponse response) throws UnsupportedEncodingException {
        //初始化一个空模型
        Model model = repositoryService.newModel();
        //设置一些默认信息
        String name = "new-process";
        String description = "";
        int revision = 1;
        String key = "process";

        ObjectNode modelNode = objectMapper.createObjectNode();
        modelNode.put(MODEL_NAME,name);
        modelNode.put(MODEL_DESCRIPTION,description);
        modelNode.put(MODEL_REVISION,revision);

        model.setName(name);
        model.setKey(key);
        model.setMetaInfo(modelNode.toString());

        repositoryService.saveModel(model);
        String id = model.getId();

        //完善ModelEditorSource
        ObjectNode editorNode = objectMapper.createObjectNode();
        editorNode.put("id","canvas");
        editorNode.put("resourceId","canvas");
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace","http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.put("stencilset",stencilSetNode);
        repositoryService.addModelEditorSource(id, editorNode.toString().getBytes("utf-8"));
        try {
            response.sendRedirect("/modeler.html?modelId="+id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/model/{modelId}/save")
    @ResponseStatus(value = HttpStatus.OK)
    public void saveModel(@PathVariable("modelId") String modelId, String name, String description, String json_xml, String svg_xml){
        try {
            Model model = repositoryService.getModel(modelId);

            ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());

            modelJson.put(MODEL_NAME, name);
            modelJson.put(MODEL_DESCRIPTION,description);
            model.setMetaInfo(modelJson.toString());
            model.setName(name);

            repositoryService.saveModel(model);
            repositoryService.addModelEditorSource(model.getId(),json_xml.getBytes("utf-8"));

            InputStream svgStream = new ByteArrayInputStream(svg_xml.getBytes("utf-8"));
            TranscoderInput input = new TranscoderInput(svgStream);

            PNGTranscoder transcoder = new PNGTranscoder();
            //设置output
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            TranscoderOutput output = new TranscoderOutput(outStream);
            //Do the transformation
            transcoder.transcode(input,output);
            final byte[] result = outStream.toByteArray();
            repositoryService.addModelEditorSource(model.getId(),result);
            outStream.close();
        } catch (Exception e) {
            logger.error("error saving model ",e);
            throw new ActivitiException("Error saving model",e);
        }

    }
}
