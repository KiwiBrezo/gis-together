const express = require('express')
const cors = require("cors");

require('dotenv').config({path: './.env'});

const geodataController = require("./routes/geodata-controller");

const app = express()
const port = 4000

app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cors({
    origin:'http://localhost:8080',
    credentials:true,            //access-control-allow-credentials:true
    optionSuccessStatus:200
}));

geodataController.installHandlers(app.listen(port, () => {
    console.log(`(SERVER) Server has started on port: ${port}`)
}), { prefix: '/geojson-connector' });