import Home from './components/home/Home.vue';
import CreateSolution from './components/solution/CreateSolution.vue';
import EditSolution from './components/solution/EditSolution.vue';
import Stats from './components/solution/Stats.vue';

export const routes = [

    { path: '', component: Home, theTitle: 'Home' },
    { path: '/createSolution', component: CreateSolution},
    { path: '/editSolution/:id', component: EditSolution},
    { path: '/stats', component: Stats, theTitle: 'Stats'}

];
