//var menuTree;

var menuIds;
$(function() {
    getMenuTreeData();
    validateRule();
});
$.validator.setDefaults({
    submitHandler : function() {
        getAllSelectNodes();
        save();
    }
});

function getAllSelectNodes() {
    var ref = $('#menuTree').jstree(true); // 获得整个树

    menuIds = ref.get_selected(); // 获得所有选中节点的，返回值为数组

    $("#menuTree").find(".jstree-undetermined").each(function(i, element) {
        menuIds.push($(element).closest('.jstree-node').attr("id"));
    });
}
function getMenuTreeData() {
    $.ajax({
        type : "GET",
        url : "/sys/menu/tree",
        success : function(menuTree) {
            loadMenuTree(menuTree);
        }
    });
}


/**
 * menuTree是采用json数组的格式封装数据，然后jstree()会渲染，如果不设置core.data属性，jstree就会试着解析HTML的树节点
 * 如果你需要定义自己的插件或者下载第三方插件，你需要在相应的页面引入该资源文件，并且在"plugins"属性中激活.
 * wholerow:把每个节点作为一行，便于选中。节点过多时，在旧的浏览器上会有性能问题.
 * unique :命名唯一，不允许在同一级下有命名相同的节点. 当移动到的parent节点下，存在同名的节点时，不允许移动.
 * types:给节点增加一个类型.主要是为了便于控制和维护相同的type节点的配置。 节点设置type属性时，匹配types对象中相同的属性.
 */
function loadMenuTree(menuTree) {
    $('#menuTree').jstree({
        'core' : {
            'data' : menuTree
        },
        "checkbox" : {
            "three_state" : true,
        },
        "plugins" : [ "wholerow", "checkbox" ]
    });
    //$('#menuTree').jstree("open_all");

}

function save() {
    $('#menuIds').val(menuIds);
    var role = $('#signupForm').serialize();
    $.ajax({
        cache : true,
        type : "POST",
        url : "/sys/role/save",
        data : role, // 你的formid

        async : false,
        error : function(request) {
            alert("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引

                parent.layer.close(index);

            } else {
                parent.layer.msg(data.msg);
            }
        }
    });
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules : {
            roleName : {
                required : true
            }
        },
        messages : {
            roleName : {
                required : icon + "请输入角色名"
            }
        }
    });
}