webpackJsonp([12],{Otiw:function(e,t){},eMkR:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a={name:"DeleteModal",data:function(){return{state:!1}},methods:{handlerOnOk:function(){this.$emit("handlerOnOk")},hanlderOnCancel:function(){this.$emit("handlerOnCancel")}},watch:{deleteState:function(e){this.state=this.deleteState}},props:{deleteState:Boolean}},l={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("Modal",{attrs:{title:"","ok-text":"确定","cancel-text":"取消","class-name":"vertical-center-modal"},on:{"on-ok":e.handlerOnOk,"on-cancel":e.hanlderOnCancel},model:{value:e.state,callback:function(t){e.state=t},expression:"state"}},[n("p",[e._v("确定删除吗？")])])},staticRenderFns:[]};var c=n("VU/8")(a,l,!1,function(e){n("Otiw")},null,null);t.default=c.exports}});