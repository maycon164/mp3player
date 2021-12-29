const express = require('express');
const request = require('request');

const app = express();

app.use(express.json());
app.use(express.urlencoded({}));

app.listen(8080, () => {
    console.log("SERVIDOR RODANDO NA PORTA 8080");
});

app.get("", (req, res) => {
    res.status(404).json({
        message: "nibiruuu!!!!!"
    })
})

app.get("/letra", (req, res) => {

    let art = (req.body.art ? req.body.art : req.query.art);
    let music = (req.body.music ? req.body.music : req.query.music);

    if (!art || !music) {
        return res.status(500).json(
            { ans: "Preencha todos os campos" }
        )
    }

    let url = `https://api.vagalume.com.br/search.php?art=${art}&mus=${music}`;

    // i need to catch the lyrics from api vagalumes
    request(url, (error, response, body) => {

        if (!error && response.statusCode == 200) {
            let obj = JSON.parse(body);

            if (obj.mus[0]) {
                lyrics = obj.mus[0].text;
                paragraphs = lyrics.replace(/\n/g, '<br />')

                return res.status(200).send(`<p>${paragraphs}</p>`);
            }

            return res.status(404).json("Não Encontrei essa musica")
        }

    });

});