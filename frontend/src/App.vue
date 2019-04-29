<template>
	<div class="app">

		<my-menu :rotas='menuRoutes'/>
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

		computed: {
			menuRoutes() {
				return this.routes.filter(r => r.theTitle);
			}
		}
	}
</script>


<style>
</style>
