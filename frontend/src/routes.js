import Home from './components/home/Home.vue';
import EditScript from './components/script/EditScript.vue';
import Stats from './components/stats/Stats.vue';
import CreateScript from './components/script/CreateScript.vue';
import ScriptList from './components/script/ScriptList.vue';

export const routes = [

    { path: '', component: Home, theTitle: 'Discover' },
    { path: '/listScripts', component: ScriptList, theTitle: 'Scripts'},
    { path: '/editScript/:id', component: EditScript},
    { path: '/stats', component: Stats, theTitle: 'Stats'},
    { path: '/createScript', component: CreateScript},
    

];
