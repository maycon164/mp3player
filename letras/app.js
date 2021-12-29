const express = require('express');
const request = require('request');

const app = express();

app.use(express.json());
app.use(express.urlencoded({}));

app.listen(8080, () => {
    console.log("SERVIDOR RODANDO NA PORTA 8080");
});

app.get("/letra", (req, res) => {

    let art = req.body.artista;
    let musica = req.body.musica;

    console.log(art, musica);

    if (!art || !musica) {
        return res.status(500).json(
            { resposta: "Preencha todos os campos" }
        )
    }

    let url = `https://api.vagalume.com.br/search.php?art=${art}&mus=${musica}`;

    let letra;
    // preciso pegar a letra na api do vagalumes
    request(url, (error, response, body) => {

        if (!error && response.statusCode == 200) {
            let obj = JSON.parse(body);

            if (obj.mus[0]) {
                letra = obj.mus[0].text;
                return res.status(200).json({ letra });
            }

            return res.status(404).json("Não Encontrei essa musica")
        }

    });

});