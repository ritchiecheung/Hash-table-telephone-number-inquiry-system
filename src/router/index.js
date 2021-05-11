import Vue from 'vue'
import Router from 'vue-router'
import Add from '@/components/add.vue'
import 'element-ui/lib/theme-chalk/index.css'
import ElementUI from 'element-ui'
import Axios from 'axios'
import Index from '@/components/index.vue'
import Main from '@/components/main.vue'
import Search from '@/components/search.vue'
import Update from '@/components/update.vue'
Vue.prototype.$axios = Axios;
Vue.use(Router)
Vue.use(ElementUI)
export default new Router({
    mode:"history",
    routes: [
        {
            path: '/',
            name: '首页',
            component: Index,
            redirect:'/main',
            children:[
                {
                    path:'/add',
                    name : '添加数据',
                    component:Add,
                },
                {
                    path:'/main',
                    name : '首页',
                    component:Main,
                },
                {
                    path:'/search',
                    name:'搜索数据',
                    component:Search,
                },
                {
                    path:'/update',
                    name : '修改数据',
                    component:Update,
                },
            ]
        },

    ]
})
