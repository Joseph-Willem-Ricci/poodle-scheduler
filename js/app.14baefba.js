(function(){"use strict";var t={4146:function(t,e,n){var a=n(9242),i=n(3396),o=n(7139);const r={id:"app"},l={class:"wrap"},u=(0,i._)("button",{type:"submit"},"Submit Schedule",-1),s={key:0},d=(0,i._)("h2",null,"Schedule",-1),c={class:"availabilities"},h=(0,i._)("h2",null,"Submitted Schedules",-1),m=["onClick"];function p(t,e,n,p,f,b){const v=(0,i.up)("Calendar");return(0,i.wg)(),(0,i.iD)("div",r,[(0,i._)("div",l,[(0,i.Wm)(v,{modelValue:f.selectedDates,"onUpdate:modelValue":e[0]||(e[0]=t=>f.selectedDates=t),minDate:f.nextMonthFirstDay,maxDate:f.nextMonthLastDay,selectionMode:"multiple",inline:""},null,8,["modelValue","minDate","maxDate"]),(0,i._)("form",{onSubmit:e[4]||(e[4]=(0,a.iM)(((...t)=>b.handleSubmit&&b.handleSubmit(...t)),["prevent"]))},[(0,i.wy)((0,i._)("input",{"onUpdate:modelValue":e[1]||(e[1]=t=>f.name=t),placeholder:"Name",required:""},null,512),[[a.nr,f.name]]),(0,i.wy)((0,i._)("input",{"onUpdate:modelValue":e[2]||(e[2]=t=>f.quota=t),placeholder:"Quota",required:"",onBlur:e[3]||(e[3]=(...t)=>b.validateQuota&&b.validateQuota(...t))},null,544),[[a.nr,f.quota,void 0,{number:!0}]]),u],32),(0,i._)("button",{onClick:e[5]||(e[5]=(...t)=>b.submitAll&&b.submitAll(...t))},"Submit All"),f.responseData?((0,i.wg)(),(0,i.iD)("div",s,[d,(0,i.Uk)(" "+(0,o.zw)(f.responseData)+" ",1)])):(0,i.kq)("",!0),(0,i._)("div",c,[h,((0,i.wg)(!0),(0,i.iD)(i.HY,null,(0,i.Ko)(f.availabilities,((t,e)=>((0,i.wg)(),(0,i.iD)("div",{key:e},[(0,i._)("h3",null,(0,o.zw)(t.name),1),(0,i._)("p",null,"Quota: "+(0,o.zw)(t.quota),1),(0,i._)("p",null,"Dates: "+(0,o.zw)(t.dates.sort(((t,e)=>t-e)).map((t=>t.getDate())).join(", ")),1),(0,i.Uk)(),(0,i._)("button",{onClick:t=>b.editSchedule(e)},"Edit",8,m)])))),128))])])])}n(560);var f=n(2845),b=n(1076),v={components:{Calendar:f["default"]},data(){return{name:"",selectedDates:[],availabilities:[],quota:null,editIndex:null,availability:null,responseData:null,nextMonthLastDay:null,nextMonthFirstDay:null}},created(){const t=new Date,e=t.getMonth()+1;this.nextMonthFirstDay=new Date(t.getFullYear(),e,1),this.nextMonthLastDay=new Date(t.getFullYear(),e+1,0)},methods:{validateQuota(){const t=this.nextMonthLastDay.getDate();(this.quota<0||this.quota>t||!Number.isInteger(this.quota))&&(alert(`Quota must be an integer between 0 and ${t}`),this.quota=null)},handleSubmit(){const t={name:this.name,quota:this.quota,dates:this.selectedDates.map((t=>new Date(t)))};null!==this.editIndex?(this.availabilities[this.editIndex]=t,this.editIndex=null):this.availabilities.push(t),this.resetForm()},resetForm(){this.name="",this.quota=null,this.selectedDates=[]},editSchedule(t){const e=this.availabilities[t];this.editIndex=t,this.name=e.name,this.quota=e.quota,this.selectedDates=e.dates},async submitAll(){try{const t=this.availabilities.map((t=>{const e=t.dates.map((t=>{const e=new Date(t);return e.getDate()}));return{...t,dates:e}})),e={employeeList:t,numberOfDays:this.nextMonthLastDay.getDate()},n=await b.Z.post("http://localhost:8080/api/process",e);200===n.status?this.responseData=n.data:500===n.status?(console.error("Error: ",n.data),alert("Server error: "+n.data.message)):alert("Something went wrong")}catch(t){console.error("Error: ",t),alert(t.response.data)}}}},D=n(89);const y=(0,D.Z)(v,[["render",p]]);var g=y;(0,a.ri)(g).mount("#app")}},e={};function n(a){var i=e[a];if(void 0!==i)return i.exports;var o=e[a]={exports:{}};return t[a].call(o.exports,o,o.exports,n),o.exports}n.m=t,function(){var t=[];n.O=function(e,a,i,o){if(!a){var r=1/0;for(d=0;d<t.length;d++){a=t[d][0],i=t[d][1],o=t[d][2];for(var l=!0,u=0;u<a.length;u++)(!1&o||r>=o)&&Object.keys(n.O).every((function(t){return n.O[t](a[u])}))?a.splice(u--,1):(l=!1,o<r&&(r=o));if(l){t.splice(d--,1);var s=i();void 0!==s&&(e=s)}}return e}o=o||0;for(var d=t.length;d>0&&t[d-1][2]>o;d--)t[d]=t[d-1];t[d]=[a,i,o]}}(),function(){n.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return n.d(e,{a:e}),e}}(),function(){n.d=function(t,e){for(var a in e)n.o(e,a)&&!n.o(t,a)&&Object.defineProperty(t,a,{enumerable:!0,get:e[a]})}}(),function(){n.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(t){if("object"===typeof window)return window}}()}(),function(){n.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)}}(),function(){n.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})}}(),function(){var t={143:0};n.O.j=function(e){return 0===t[e]};var e=function(e,a){var i,o,r=a[0],l=a[1],u=a[2],s=0;if(r.some((function(e){return 0!==t[e]}))){for(i in l)n.o(l,i)&&(n.m[i]=l[i]);if(u)var d=u(n)}for(e&&e(a);s<r.length;s++)o=r[s],n.o(t,o)&&t[o]&&t[o][0](),t[o]=0;return n.O(d)},a=self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[];a.forEach(e.bind(null,0)),a.push=e.bind(null,a.push.bind(a))}();var a=n.O(void 0,[998],(function(){return n(4146)}));a=n.O(a)})();
//# sourceMappingURL=app.14baefba.js.map