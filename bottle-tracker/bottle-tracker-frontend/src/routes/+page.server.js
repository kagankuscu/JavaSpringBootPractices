export async function load() {
    const res = await fetch("http://localhost:8080/api/bottles/first");

    if (res.status !== 200) {
        return {
            id: 0,
            multiply: 1,
            count: 0
        }
    }
    return await res.json();
}