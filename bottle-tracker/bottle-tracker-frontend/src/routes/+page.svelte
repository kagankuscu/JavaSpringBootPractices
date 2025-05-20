<script>
    import Header from "../components/header.svelte";
    const { data } = $props();

    let { id, multiply, count } = $state(data);

	const colors = [
	  "#FF0000", // Step 1: Red
	  "#FF1A00", // Step 2
	  "#FF3300", // Step 3
	  "#FF4D00", // Step 4
	  "#FF6600", // Step 5
	  "#FF8000", // Step 6
	  "#FF9900", // Step 7
	  "#FFB200", // Step 8
	  "#FFCC00", // Step 9
	  "#00FF00"  // Step 10: Green
	];

	async function add() {
		count += 1;

		if (count === 41) {
			count = 1;
			multiply += 1;
		}

        await save({count, multiply});
	}

	async function remove() {
		if (disable())
			return;

		if (multiply === 1 && count <= 0) {
			alert("Count is 0");
			return;
		} else if (multiply > 1 && count === 1) {
			count = 40;
			multiply -= 1;
			return;
		}
		count -= 1;

        await save({count, multiply});
	}

	async function reset() {
		multiply = 1;
		count = 0;

        await save({multiply, count});
	}

	function getColor() {
		const divided = Math.floor((count / 40) * 9);
		if (count >= 40) {
			return colors[colors.length - 1];
		}
		return colors[divided];
	}

	function disable() {
		return (multiply === 1 && count === 0);
    }

    async function save(bottle) {
        bottle = {
            count,
            multiply
        }

        if (id !== 0)
            bottle["id"] = id;

        const data = await fetch("http://localhost:8080/api/bottles", {
            method: "POST",
                headers: {
                    "Content-Type": "Application/json"
                },
                body: JSON.stringify(bottle),
        });

        const jData = await data.json();
        id = jData.id;
        return jData;
    }
</script>

<Header
    name="Bottle Tracker" />

<div class="body">
    <div class="container">
        <div class="info">
            <p>{multiply}</p>
            <p class="multiply">x</p>
            <h1 class="count" style="color: {getColor()}">{count}</h1>
        </div>
        <div class="btn-container">
            <button class="btn btn-add"
                onclick={add}>Add</button>
            <button
                class="btn btn-remove {disable() ? 'disable' : ''}"
                onclick={remove}
                >
                Remove
            </button>
            <button class="btn btn-reset"
                onclick={reset}>Reset</button>
        </div>
    </div>
</div>
