<template>
	<div class="app">

<!-- 		<nav>
			<ul> -->

				<!-- a faz o reload da pagina -->
				<!-- <li><a href="/">Home</a></li>
				<li><a href="/createSolution">Create Solution</a></li> -->

				<!-- <li><router-link to="/">Home</router-link></li>
				<li><router-link to="/createSolution">Create Solution</router-link></li> -->

<!-- 				<li v-for="route in routes">
					<router-link :to="route.path ? route.path : '/'">{{route.theTitle}}</router-link>
				</li> -->


		<!-- 	</ul>
		</nav> -->

		<my-menu :rotas='routes'/>
		<router-view/>

	</div>
</template>


<script>
	import { routes }  from './routes';
	import Menu from './components/menu/Menu.vue';

	export default {

		components: {
			'my-menu': Menu
		},

		data () {
			return {
				routes
			}
		},
	}

</script>


<style>
.app {

    /*background: radial-gradient(circle farthest-side at 0% 50%, white 23.5%, rgba(240,166,17,0) 0) 21px 30px,radial-gradient(circle farthest-side at 0% 50%, #F4F4F4 24%, rgba(240,166,17,0) 0) 19px 30px,linear-gradient(white 14%, rgba(240,166,17,0) 0, rgba(240,166,17,0) 85%, white 0) 0 0,linear-gradient(150deg, white 24%, #F4F4F4 0, #F4F4F4 26%, rgba(240,166,17,0) 0, rgba(240,166,17,0) 74%, #F4F4F4 0, #F4F4F4 76%, white 0) 0 0,linear-gradient(30deg, white 24%, #F4F4F4 0, #F4F4F4 26%, rgba(240,166,17,0) 0, rgba(240,166,17,0) 74%, #F4F4F4 0, #F4F4F4 76%, white 0) 0 0,linear-gradient(90deg, #F4F4F4 2%, white 0, white 98%, #F4F4F4 0%) 0 0 white;
    background-size: 40px 60px;*/
}
</style>
