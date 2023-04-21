const express = require("express");
const axios = require("axios");

const router = express.Router();

router.post('/file', async (req, res) => {
    try {
        const response = await axios.post(`${process.env.GIS_GEO_IMPORTER_URL}/api/v1/file`, req.body || {}, {
            headers :{ "authorization" : req.headers["authorization"] }
        });
        res.send(response.data);
    } catch (e) {
        res.status(e.response.status || 500).send(e.message);
    }
});

router.get('/file/:id', async (req, res) => {
    try {
        const response = await axios.get(`${process.env.GIS_GEO_IMPORTER_URL}/api/v1/file/${req.params.id}`, {
            headers :{ "authorization" : req.headers["authorization"] }
        });
        res.send(response.data);
    } catch (e) {
        res.status(e.response.status || 500).send(e.message);
    }
});

router.delete('/file/:id', async (req, res) => {
    try {
        const response = await axios.delete(`${process.env.GIS_GEO_IMPORTER_URL}/api/v1/file/${req.params.id}`, {
            headers :{ "authorization" : req.headers["authorization"] }
        });
        res.send(response.data);
    } catch (e) {
        res.status(e.response.status || 500).send(e.message);
    }
});

module.exports = router;