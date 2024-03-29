const express = require('express')
const cors = require("cors");

require('dotenv').config({path: './.env'});

const accountServiceController = require("./routes/account-service-controller");
const importerController = require("./routes/imported-controller");

const app = express()
const port = 3000

app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cors({
    origin:'*',
    credentials:true,            //access-control-allow-credentials:true
    optionSuccessStatus:200
}));

app.use("/api/v1/account", accountServiceController);
app.use("/api/v1/importer", importerController);

app.listen(port, () => {
    console.log(`(SERVER) Server has started on port: ${port}`)
})