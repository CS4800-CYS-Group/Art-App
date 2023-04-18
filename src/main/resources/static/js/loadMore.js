async function getData(){
    for( let i = 0; i < 10; i++) {
        document.getElementById("main-container").innerHTML += "<div class=\"card mb-3\">\n            " +
            "<div class=\"card-header\">\n                " +
            "<img class=\"artistPostImg\" src=\"assets/artist_stick_1.PNG\" alt=\"Artist Profile Pic\">\n                " +
            "<a href=\"\">    &#64;Artist Handle</a>\n            " +
            "</div>\n            " +
            "<div class=\"card-body\">\n                " +
            "<h5 class=\"card-title\" text-align=\"center\">Post Title</h5>\n                " +
            "<p class=\"card-text\">The start of the frontend. It's getting there....</p>\n                " +
            "<div class=\"postWidget\">\n                    " +
            "<button class=\"btn-icon\"><span class=\"material-symbols-outlined\">favorite</span></button>\n                    " +
            "<button class=\"btn-icon\"><span class=\"material-symbols-outlined\">thumb_up</span></button>\n                    " +
            "<button class=\"btn-icon\"><span class=\"material-symbols-outlined\">share</span></button>\n                " +
            "</div>\n            " +
            "</div>\n        " +
            "</div>"
    }

}