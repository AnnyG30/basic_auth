// Cambia estas URLs si tu backend usa otras rutas
const REGISTER_URL = '/api/register';
const LOGIN_URL = '/api/me'; // Si cambiaste el endpoint en el backend, cambia esto.

/**
 * Pestañas Login / Registro
 */
const tabLogin = document.getElementById('tab-login');
const tabRegister = document.getElementById('tab-register');
const loginPanel = document.getElementById('login-panel');
const registerPanel = document.getElementById('register-panel');

tabLogin.addEventListener('click', () => {
    tabLogin.classList.add('active');
    tabRegister.classList.remove('active');
    loginPanel.classList.add('active');
    registerPanel.classList.remove('active');
});

tabRegister.addEventListener('click', () => {
    tabRegister.classList.add('active');
    tabLogin.classList.remove('active');
    registerPanel.classList.add('active');
    loginPanel.classList.remove('active');
});

/**
 * LOGIN
 */
const loginForm = document.getElementById('login-form');
const loginResult = document.getElementById('login-result');

loginForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    loginResult.textContent = '';
    loginResult.className = 'result';

    const username = document.getElementById('login-username').value.trim();
    const password = document.getElementById('login-password').value;

    if (!username || !password) {
        loginResult.textContent = 'Rellena usuario y contraseña.';
        loginResult.classList.add('error');
        return;
    }

    try {
        const credentials = btoa(username + ':' + password); // Basic Auth

        const response = await fetch(LOGIN_URL, {
            method: 'GET',
            headers: {
                'Authorization': 'Basic ' + credentials
            }
        });

        if (!response.ok) {
            if (response.status === 401) {
                throw new Error('Credenciales incorrectas.');
            } else {
                throw new Error('Error al hacer login. Código: ' + response.status);
            }
        }

        const data = await response.json();
        loginResult.textContent = `Login correcto. Usuario: ${data.username}, rol: ${data.role}`;
        loginResult.classList.add('success');

    } catch (err) {
        loginResult.textContent = err.message;
        loginResult.classList.add('error');
    }
});

/**
 * REGISTRO
 */
const registerForm = document.getElementById('register-form');
const registerResult = document.getElementById('register-result');

registerForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    registerResult.textContent = '';
    registerResult.className = 'result';

    const username = document.getElementById('register-username').value.trim();
    const password = document.getElementById('register-password').value;

    if (!username || !password) {
        registerResult.textContent = 'Rellena usuario y contraseña.';
        registerResult.classList.add('error');
        return;
    }

    try {
        const response = await fetch(REGISTER_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, password })
        });

        const text = await response.text(); // puede ser texto o JSON

        if (!response.ok) {
            throw new Error(text || 'Error al registrar usuario.');
        }

        // Si tu backend devuelve JSON, puedes parsearlo:
        // const data = JSON.parse(text);

        registerResult.textContent = 'Usuario registrado correctamente. Ahora puedes hacer login.';
        registerResult.classList.add('success');

        // Opcional: cambiar a pestaña login automáticamente
        tabLogin.click();

    } catch (err) {
        registerResult.textContent = err.message;
        registerResult.classList.add('error');
    }
});
