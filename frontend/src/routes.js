import Home from './components/home/Home.vue';
import CreateSolution from './components/solution/CreateSolution.vue';

export const routes = [

    { path: '', component: Home, theTitle: 'Home' },
    { path: '/createSolution', component: CreateSolution, theTitle: 'Create Solution'}

];
