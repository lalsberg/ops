<template>

	<nav>
		<div class="underline"></div>
		<div class="underline"></div>
		<div class="underline"></div>
		<div v-for="(route, index) in rotas" @click="ul(index)" class="external-element">
			<router-link :to="route.path ? route.path : '/'">
				<div class="element">
					{{route.theTitle}}
				</div>
			</router-link>
		</div>
	</nav>

</template>


<script>
	export default {

		props: {
			rotas: {
				required: true
			}
		},

		methods: {
			ul: function(index) {
				console.log('click!' + index)
				
				console.log(document);
				var underlines = document.querySelectorAll(".underline");

				for (var i = 0; i < underlines.length; i++) {
					underlines[i].style.transform = 'translate3d(' + index * 100 + '%,0,0)';
				}
			}
		}

	}

</script>

<style scoped>
	.external-element {
		display: inline-block;
		z-index: 10;
		width: 20%;
		text-align: center;
		cursor: pointer;
	}
	.element {
		display: inline-block;
		z-index: 10;
		width: 100%;
		padding: 1em 0;
		text-align: center;
		cursor: pointer;
	}

	.element a {
		cursor: pointer;
		text-decoration: none;
	}

	a:hover, a:visited, a:hover, a:link {
	  color: rgba(1, 1, 1, 1);
	}

	nav {
		--underline-height: .5em;
		--transition-duration: .5s;

		font-family: system-ui, sans-serif;
		background: whitesmoke;
		

		position: relative;
		white-space: nowrap;
		background: white;
		padding: var(--underline-height) 0;
		margin-bottom: 2em;
		box-shadow: 0 1em 2em rgba(0, 0, 0, 0.05);
	}

	.underline {
		display: block;
		position: absolute;
		z-index: 0;
		bottom: 0;
		left: 0;
		height: var(--underline-height);
		width: 20%;
		background: black;
		pointer-events: none;
		mix-blend-mode: multiply;
		transition: -webkit-transform var(--transition-duration) ease-in-out;
		transition: transform var(--transition-duration) ease-in-out;
		transition: transform var(--transition-duration) ease-in-out, -webkit-transform var(--transition-duration) ease-in-out;
	}
	.underline:nth-child(1) {
		background: yellow;
		transition: calc(var(--transition-duration) * .8);
	}
	.underline:nth-child(2) {
		background: cyan;
		transition: calc(var(--transition-duration) * 1.2);
	}
	.underline:nth-child(3) {
		background: magenta;
	}
</style>