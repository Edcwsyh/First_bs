$('#checkedLevel').multipleSelect({
　　　　addTitle: true, //鼠标点悬停在下拉框时是否显示被选中的值
　　　　selectAll: false, //是否显示全部复选框，默认显示
　　　　name: "质控级别",
　　　　selectAllText: "选择全部", //选择全部的复选框的text值
　　　　allSelected: "全部", //全部选中后显示的值
　　　　//delimiter: ', ', //多个值直接的间隔符，默认是逗号
　　　　placeholder: "质控级别" //不选择时下拉框显示的内容
　　});

//设置默认选中:其中数组中多个值用逗号分隔，值是option的value
$("#checkedLevel").multipleSelect('setSelects', [1001,1002]);

 $('#selectJcjb').multipleSelect("close");//其他的方法，可到js中去查看方法名，根据实际情况进行调用。