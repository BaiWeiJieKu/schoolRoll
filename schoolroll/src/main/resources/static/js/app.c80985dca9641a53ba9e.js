webpackJsonp([10],{NHnr:function(e,n,t){"use strict";Object.defineProperty(n,"__esModule",{value:!0});var a=t("//Fk"),o=t.n(a),i={render:function(){var e=this.$createElement,n=this._self._c||e;return n("div",{attrs:{id:"app"}},[n("router-view")],1)},staticRenderFns:[]};var r=t("VU/8")({name:"App"},i,!1,function(e){t("YWvu")},null,null).exports,c=t("YaEn");Vue.config.productionTip=!1,Vue.use(iview),axios.interceptors.response.use(function(e){var n=e.data;if(500==n.code)iview.Notice.error({desc:n.message,duration:0});else if(401==n.code)c.a.replace("/");else if(200==n.code)return n},function(e){return o.a.reject(e)}),axios.defaults.withCredentials=!0,new Vue({el:"#app",router:c.a,components:{App:r},template:"<App/>"})},YWvu:function(e,n){},YaEn:function(e,n,t){"use strict";Vue.use(VueRouter),n.a=new VueRouter({routes:[{path:"/",name:"Login",component:function(e){return t.e(6).then(function(){var n=[t("jT7l")];e.apply(null,n)}.bind(this)).catch(t.oe)}},{path:"/sys",name:"Index",component:function(e){return Promise.all([t.e(1),t.e(0)]).then(function(){var n=[t("TaCI")];e.apply(null,n)}.bind(this)).catch(t.oe)},children:[{path:"user",name:"User",component:function(e){return t.e(4).then(function(){var n=[t("5sQR")];e.apply(null,n)}.bind(this)).catch(t.oe)}},{path:"speciality",name:"Speciality",component:function(e){return Promise.all([t.e(0),t.e(5)]).then(function(){var n=[t("eP/5")];e.apply(null,n)}.bind(this)).catch(t.oe)}},{path:"student",name:"Student",component:function(e){return Promise.all([t.e(0),t.e(8)]).then(function(){var n=[t("NR8I")];e.apply(null,n)}.bind(this)).catch(t.oe)}},{path:"check",name:"Check",component:function(e){return Promise.all([t.e(0),t.e(2)]).then(function(){var n=[t("K1bd")];e.apply(null,n)}.bind(this)).catch(t.oe)}},{path:"state",name:"State",component:function(e){return Promise.all([t.e(0),t.e(3)]).then(function(){var n=[t("+XsF")];e.apply(null,n)}.bind(this)).catch(t.oe)}},{path:"total",name:"Total",component:function(e){return t.e(7).then(function(){var n=[t("pueF")];e.apply(null,n)}.bind(this)).catch(t.oe)}}]}]})}},["NHnr"]);