<template>
    <button @click='clickIt()' type="button" class="btn-default">
        <slot></slot>
    </button>
</template>


<script>
    export default {
        methods: {
            clickIt: function() {
                this.$emit("buttonClicked");
            }
        }
    }
</script>


<style scoped>
    .btn-default {
        cursor: pointer;
        color: #fff !important;
        background-color: #448aff;
        border-color: #448aff;
        font-weight: 500;
        font-size: 0.9rem;
        text-align: center;
        text-transform: uppercase;
        border-radius: 3px;
        box-shadow: 0 3px 1px -2px rgba(0,0,0,.2), 0 2px 2px 0 rgba(0,0,0,.14), 0 1px 5px 0 rgba(0,0,0,.12);
    }

    button.btn-default:hover {
        text-decoration: none;
    }

    button.btn-default:not([disabled]).md-focused, button.btn-default:not([disabled]):active, button.btn-default:not([disabled]):hover {
        opacity: .8;
    }

    button.btn-default[disabled] {
        background-color: rgba(0,0,0,0.12);
        border-color: rgba(0,0,0,0);
        box-shadow: none;
        color: #999 !important;
        cursor: auto;
    }
</style>
