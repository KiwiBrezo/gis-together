const express = require("express");
const axios = require("axios");

const router = express.Router();

router.post('/login', async (req, res) => {
    try {
        const jwt = await axios.post(`${process.env.GIS_ACCOUNT_SERVICE_URL}/api/v1/login`, req.body || {});
        res.send(jwt.data);
    } catch (e) {
        res.status(e.response.status || 500).send(e.message);
    }
});

router.post('/register', async (req, res) => {
    try {
        const jwt = await axios.post(`${process.env.GIS_ACCOUNT_SERVICE_URL}/api/v1/register`, req.body || {});
        res.send(jwt.data);
    } catch (e) {
        res.status(e.response.status || 500).send(e.message);
    }
});

module.exports = router;