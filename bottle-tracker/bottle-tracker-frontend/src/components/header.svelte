<script>
    let { name = "Navbar" } = $props();
</script>

<nav>
    <h1 class="title">{name}</h1>
</nav>

<style>
    nav {
        text-align: center;
    }

    h1.title {
        margin: 0;
        padding: 1rem;
        font-size: 3.25rem;
    }
</style>