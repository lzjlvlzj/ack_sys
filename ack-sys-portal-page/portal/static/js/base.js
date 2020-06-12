var PortalBase = {};
/**科室导航*/
PortalBase.ksdh = {
    init: function () {
        PortalBase.ksdh.mouseOver();
        PortalBase.ksdh.mouseOut();
    },
    mouseOver: function () {
        $(".ksdh-tab li").mouseover(function () {
            $(this).addClass("ksdh-tab-li").addClass("shadow");
        });
    },

    mouseOut: function () {
        $(".ksdh-tab li").mouseout(function () {
            $(this).removeClass("ksdh-tab-li").removeClass("shadow");
        });
    }
};