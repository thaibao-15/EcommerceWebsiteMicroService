
export async function introspect(token) {
    const res = await fetch('http://localhost:8080/identity/auth/introspect', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ token })
    });
    return res.json();

}