package io.github.aprilvuylsteke000.animalendpoints.util;

import java.util.Base64;
import java.util.List;

public class HtmlResponseBuilder {

    //this is the html for home
    public static String generateHomeHtml() {
        return """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Animal Image Endpoints Application</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        margin: 0;
                        padding: 0;
                        background-color: #f4f4f9;
                    }
                    header {
                        background-color: #cccccc;
                        color: #000000;
                        padding: 1em 0;
                        text-align: center;
                    }
                    main {
                        padding: 2em;
                        text-align: center;
                    }
                    section {
                        margin: 2em 0;
                        padding: 1em;
                        border: 1px solid #ddd;
                        border-radius: 5px;
                        background: white;
                        max-width: 800px;
                        margin-left: auto;
                        margin-right: auto;
                    }
                    h1 {
                        margin: 0;
                        font-size: 2em;
                    }
                    h2 {
                        color: #333333;
                    }
                    ul {
                        list-style-type: none;
                        padding: 0;
                    }
                    li {
                        margin: 1em 0;
                    }
                    footer {
                        margin-top: 2em;
                        padding: 1em 0;
                        background: #cccccc;
                        color: #000000;
                        text-align: center;
                    }
                    a {
                        color: #336699;
                        text-decoration: none;
                    }
                    a:hover {
                        text-decoration: underline;
                    }
                    pre {
                        background: #f4f4f4;
                        border: 1px solid #ddd;
                        border-left: 3px solid #f36d33;
                        color: #666;
                        page-break-inside: avoid;
                        font-family: monospace;
                        font-size: 15px;
                        line-height: 1.6;
                        margin-bottom: 1.6em;
                        max-width: 100%;
                        overflow: auto;
                        padding: 1em 1.5em;
                        display: block;
                        word-wrap: break-word;
                    }
                </style>
            </head>
            <body>
                <header>
                    <h1>Animal Image Endpoints Application</h1>
                </header>
                <main>
                    <section>
                        <h2>About</h2>
                        <p>Welcome to the Animal Image Endpoints Application. This API allows you to fetch and store images of various animals, including cats, dogs, and bears, for use in your applications or projects.</p>
                    </section>
                    <section>
                        <h2>Available Endpoints</h2>
                        <div align="left">
                        <ul>
                            <li>
                                <strong>Home:</strong><br />
                                <pre>GET /api/images/</pre>
                                Displays this homepage.<br /><br />
                            </li>
                            <li>
                                <strong>Retrieve and Store Images:</strong><br />
                                <pre>POST /api/images/retrieveandstore</pre>
                                Parameters:<br />
                                <ul>
                                    <li><i>animal</i> (required): Specify "cat", "dog", or "bear".</li><br />
                                    <li><i>count</i> (optional, default=1): Number of images to retrieve.</li><br /><br />
                                </ul>
                            </li>
                            <li>
                                <strong>Get Image by ID:</strong><br />
                                <pre>GET /api/images/get/{id}</pre>
                                Fetches an image by its ID.<br />
                            </li>
                        </ul>
                        </div>
                    </section>
                </main>
                <footer>
                    <p>&copy; 2024, April Vuylsteke, Animal Image Endpoints Application</p>
                </footer>
            </body>
            </html>
        """;
    }

    //this is the html on the animal image display screen
    public static String generateImageHtml(String animalName, byte[] imageData) {
        String base64Image = Base64.getEncoder().encodeToString(imageData);

        return """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Animal Image</title>
                <style>
                                        body {
                                            font-family: Arial, sans-serif;
                                            margin: 0;
                                            padding: 0;
                                            background-color: #f4f4f9;
                                        }
                                        header {
                                            background-color: #cccccc;
                                            color: #000000;
                                            padding: 1em 0;
                                            text-align: center;
                                        }
                                        main {
                                            padding: 2em;
                                            text-align: center;
                                        }
                                        section {
                                            margin: 2em auto;
                                            padding: 1em;
                                            border: 1px solid #ddd;
                                            border-radius: 5px;
                                            background: white;
                                            max-width: 800px;
                                        }
                                        h1 {
                                            color: #000000;
                                            font-size: 2em;
                                        }
                                        p {
                                            font-size: 1.2em;
                                        }
                                        footer {
                                            margin-top: 2em;
                                            padding: 1em 0;
                                            background: #cccccc;
                                            color: #000000;
                                            text-align: center;
                                        }
                                    </style>
            </head>
            <body>
                <header>
                    <h1>Animal Image Endpoints Application</h1>
                </header>
                <main>
                    <section>
                        <h2>Animal: %s</h2>
                        <img src="data:image/jpeg;base64,%s" alt="Animal Image" />
                    </section>
                </main>
                <footer>
                    <p>&copy; 2024, April Vuylsteke, Animal Image Endpoints Application</p>
                </footer>
            </body>
            </html>
        """.formatted(animalName, base64Image);
    }

    //this is the html for retrieveandstore
    public static String generateRetrieveAndStoreHtml(String animal, int count, List<String> storedImages) {
        StringBuilder imageListHtml = new StringBuilder();

        if (storedImages != null && !storedImages.isEmpty()) {
            storedImages.forEach(imageName -> {
                imageListHtml.append("<li>").append(imageName).append("</li>");
            });
        } else {
            imageListHtml.append("<li>No images stored</li>");
        }

        return """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Retrieve and Store Images</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        margin: 0;
                        padding: 0;
                        background-color: #f4f4f9;
                    }
                    header {
                        background-color: #cccccc;
                        color: #000000;
                        padding: 1em 0;
                        text-align: center;
                    }
                    main {
                        padding: 2em;
                        text-align: center;
                    }
                    section {
                        margin: 2em auto;
                        padding: 1em;
                        border: 1px solid #ddd;
                        border-radius: 5px;
                        background: white;
                        max-width: 800px;
                    }
                    ul {
                        list-style-type: none;
                        padding: 0;
                    }
                    li {
                        margin: 0.5em 0;
                    }
                    footer {
                        margin-top: 2em;
                        padding: 1em 0;
                        background: #cccccc;
                        color: #000000;
                        text-align: center;
                    }
                </style>
            </head>
            <body>
                <header>
                    <h1>Animal Image Endpoints Application</h1>
                </header>
                <main>
                    <section>
                        <h2>Retrieve and Store Images</h2>
                        <p>Animal: %s</p>
                        <p>Count: %d</p>
                        <h3>Stored Images:</h3>
                        <ul>
                            %s
                        </ul>
                    </section>
                </main>
                <footer>
                    <p>&copy; 2024, April Vuylsteke, Animal Image Endpoints Application</p>
                </footer>
            </body>
            </html>
        """.formatted(animal, count, imageListHtml.toString());
    }

    public static String generateErrorHtml(String errorMessage) {
        return """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Error</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 0;
                    padding: 0;
                    background-color: #f4f4f9;
                }
                header {
                    background-color: #cccccc;
                    color: #000000;
                    padding: 1em 0;
                    text-align: center;
                }
                main {
                    padding: 2em;
                    text-align: center;
                }
                section {
                    margin: 2em auto;
                    padding: 1em;
                    border: 1px solid #ddd;
                    border-radius: 5px;
                    background: white;
                    max-width: 800px;
                }
                ul {
                    list-style-type: none;
                    padding: 0;
                }
                li {
                    margin: 0.5em 0;
                }
                footer {
                    margin-top: 2em;
                    padding: 1em 0;
                    background: #cccccc;
                    color: #000000;
                    text-align: center;
                }
            </style>
        </head>
        <body>
            <header>
                <h1>Error</h1>
            </header>
            <main>
                <section>
                    <h2>An Error Occurred</h2>
                    <p>%s</p>
                </section>
            </main>
            <footer>
                <p>&copy; 2024, April Vuylsteke, Animal Image Endpoints Application</p>
            </footer>
        </body>
        </html>
    """.formatted(errorMessage);
    }

}
