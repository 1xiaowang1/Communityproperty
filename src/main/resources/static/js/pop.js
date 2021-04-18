/**
 * function: 产生弹出框
 * author: 王欢
 * time: 2020-4-17
 * describe: 依赖jquery
 */
var winWidth,
    winHeight,
    docWidth,
    docHeight,
    popWidth,
    popHeight,
    popTop,
    popLeft,
    parentId,
    popId,
    text;

/**
 * 创建pop
 * @param parentId：存放弹出框的容器ID，里面不能包含任何代码，包括空格，换行，注释等。
 * @param popId：弹出框的ID。
 * @param popWidth：pop框的宽度
 * @param popHeight：pop框的高度
 * @param popHeight：pop框的具体html代码
 */
function openPop(parentId, popId, popWidth, popHeight, text) {
    this.parentId = parentId;
    this.popId = popId;
    this.text = text;
    this.popWidth = popWidth;
    this.popHeight = popHeight;
    setPopPosition();
    createPop(parentId, popId);
}

/**
 * 设置弹出框位置，这里为屏幕正中。
 * @param pop
 * @param popWidth
 * @param popHeight
 */
function setPopPosition() {
    winWidth = $(window).width();
    winHeight = $(window).height();
    docWidth = $(document).width();
    docHeight = $(document).height();
    popTop = (winHeight - popHeight) / 2;
    popLeft = (winWidth - popWidth) / 2;
}

function createPop(parentId, popId) {
    var str = '<div class="yy-cover" style="background-color: rgba(0,0,0,0.3);z-index: 100;display: none;position: absolute;top:0;left: 0;"></div>' + text;
    $(parentId).html(str);
    $(".yy-cover").css({"display": "block", "width": winWidth + "px", "height": docHeight + "px"});
    $(popId).css({"display": "block", "top": popTop + "px", "left": popLeft + "px"});
}

/**
 * 改变窗口大小时重置pop框的位置
 */
$(window).resize(function () {
    setPopPosition();
    if ($(parentId).html() != "") {
        $(".yy-cover").css({"display": "block", "width": winWidth + "px", "height": docHeight + "px"});
        $(popId).css({"display": "block", "top": popTop + "px", "left": popLeft + "px", "transition": "all .5s ease"});
    }
})