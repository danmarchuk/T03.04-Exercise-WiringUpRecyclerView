package com.example.android.popular_movies;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {
    public static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private static final String SAMPLE_JSON_RESPONSE = "{\"page\":1,\"total_results\":10000,\"total_pages\":500,\"results\":[{\"popularity\":247.998,\"vote_count\":308,\"video\":false,\"poster_path\":\"\\/mhDdx7o7hhrxrikq8aqPLLnS9w8.jpg\",\"id\":475430,\"adult\":false,\"backdrop_path\":\"\\/o0F8xAt8YuEm5mEZviX5pEFC12y.jpg\",\"original_language\":\"en\",\"original_title\":\"Artemis Fowl\",\"genre_ids\":[12,14,878,10751],\"title\":\"Artemis Fowl\",\"vote_average\":6,\"overview\":\"With the help of his loyal protector Butler, 12-year-old genius Artemis Fowl, descendant of a long line of criminal masterminds, seeks to find his mysteriously disappeared father, and in doing so, uncovers an ancient, underground civilization—the amazingly advanced world of fairies. Deducing that his father’s disappearance is somehow connected to the secretive, reclusive fairy world, cunning Artemis concocts a dangerous plan—so dangerous that he ultimately finds himself in a perilous war of wits with the all-powerful fairies.\",\"release_date\":\"2020-06-12\"},{\"popularity\":191.408,\"vote_count\":3753,\"video\":false,\"poster_path\":\"\\/xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg\",\"id\":419704,\"adult\":false,\"backdrop_path\":\"\\/5BwqwxMEjeFtdknRV792Svo0K1v.jpg\",\"original_language\":\"en\",\"original_title\":\"Ad Astra\",\"genre_ids\":[18,878],\"title\":\"Ad Astra\",\"vote_average\":6.1,\"overview\":\"The near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown.\",\"release_date\":\"2019-09-17\"},{\"popularity\":127.845,\"vote_count\":9,\"video\":false,\"poster_path\":\"\\/1Duc3EBiegywczxTWekvy03HWai.jpg\",\"id\":554993,\"adult\":false,\"backdrop_path\":\"\\/oCFbh4Mrd0fuGanCgIF6sG27WGD.jpg\",\"original_language\":\"sv\",\"original_title\":\"Britt-Marie var här\",\"genre_ids\":[35,18],\"title\":\"Britt-Marie Was Here\",\"vote_average\":4.3,\"overview\":\"Britt-Marie, a woman in her sixties, decides to leave her husband and start anew. Having been housewife for most of her life and and living in small backwater town of Borg, there isn't many jobs available and soon she finds herself fending a youth football team.\",\"release_date\":\"2019-01-25\"},{\"popularity\":125.825,\"vote_count\":7980,\"video\":false,\"poster_path\":\"\\/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg\",\"id\":496243,\"adult\":false,\"backdrop_path\":\"\\/ApiBzeaa95TNYliSbQ8pJv4Fje7.jpg\",\"original_language\":\"ko\",\"original_title\":\"기생충\",\"genre_ids\":[35,18,53],\"title\":\"Parasite\",\"vote_average\":8.5,\"overview\":\"All unemployed, Ki-taek's family takes peculiar interest in the wealthy and glamorous Parks for their livelihood until they get entangled in an unexpected incident.\",\"release_date\":\"2019-05-30\"},{\"popularity\":125.087,\"vote_count\":4640,\"video\":false,\"poster_path\":\"\\/aQvJ5WPzZgYVDrxLX4R6cLJCEaQ.jpg\",\"id\":454626,\"adult\":false,\"backdrop_path\":\"\\/stmYfCUGd8Iy6kAMBr6AmWqx8Bq.jpg\",\"original_language\":\"en\",\"original_title\":\"Sonic the Hedgehog\",\"genre_ids\":[28,35,878,10751],\"title\":\"Sonic the Hedgehog\",\"vote_average\":7.5,\"overview\":\"Based on the global blockbuster videogame franchise from Sega, Sonic the Hedgehog tells the story of the world’s speediest hedgehog as he embraces his new home on Earth. In this live-action adventure comedy, Sonic and his new best friend team up to defend the planet from the evil genius Dr. Robotnik and his plans for world domination.\",\"release_date\":\"2020-02-12\"},{\"popularity\":124.496,\"id\":581859,\"video\":false,\"vote_count\":153,\"vote_average\":6.9,\"title\":\"Da 5 Bloods\",\"release_date\":\"2020-06-12\",\"original_language\":\"en\",\"original_title\":\"Da 5 Bloods\",\"genre_ids\":[10752,18],\"backdrop_path\":\"\\/Aq5Zhj9iaTF6BEKNk05dlUxeHKa.jpg\",\"adult\":false,\"overview\":\"Four African-American Vietnam veterans return to Vietnam. They are in search of the remains of their fallen squad leader and the promise of buried treasure. These heroes battle forces of humanity and nature while confronted by the lasting ravages of the immorality of the Vietnam War.\",\"poster_path\":\"\\/yx4cp1ljJMDSFeEex0Zjv45b55E.jpg\"},{\"popularity\":102.578,\"vote_count\":0,\"video\":false,\"poster_path\":\"\\/4dS40km5rcd4udAGJheU18ZeSfi.jpg\",\"id\":634649,\"adult\":false,\"backdrop_path\":null,\"original_language\":\"en\",\"original_title\":\"Untitled Spider-Man Sequel\",\"genre_ids\":[28,12,878],\"title\":\"Untitled Spider-Man Sequel\",\"vote_average\":0,\"overview\":\"Sony Pictures Entertainment and The Walt Disney Company jointly announced that Marvel Studios and its President Kevin Feige will produce the third film in the \\\"Spider-Man: Homecoming\\\" series, starring Tom Holland. The film is scheduled to release on July 16, 2021.  The third film will see Peter Parker deal with the aftermath of Quentin Beck outing him as Spider-man, but also to deal with a familiar old foe who has joined a group of \\\"Sinister Six\\\" villains.\",\"release_date\":\"2021-11-04\"},{\"popularity\":97.743,\"vote_count\":4545,\"video\":false,\"poster_path\":\"\\/h4VB6m0RwcicVEZvzftYZyKXs6K.jpg\",\"id\":495764,\"adult\":false,\"backdrop_path\":\"\\/kvbbK2rLGSJh9rf6gg1i1iVLYQS.jpg\",\"original_language\":\"en\",\"original_title\":\"Birds of Prey (and the Fantabulous Emancipation of One Harley Quinn)\",\"genre_ids\":[28,35,80],\"title\":\"Birds of Prey (and the Fantabulous Emancipation of One Harley Quinn)\",\"vote_average\":7.2,\"overview\":\"Harley Quinn joins forces with a singer, an assassin and a police detective to help a young girl who had a hit placed on her after she stole a rare diamond from a crime lord.\",\"release_date\":\"2020-02-05\"},{\"popularity\":89.114,\"vote_count\":914,\"video\":false,\"poster_path\":\"\\/wxPhn4ef1EAo5njxwBkAEVrlJJG.jpg\",\"id\":514847,\"adult\":false,\"backdrop_path\":\"\\/naXUDz0VGK7aaPlEpsuYW8kNVsr.jpg\",\"original_language\":\"en\",\"original_title\":\"The Hunt\",\"genre_ids\":[28,27,53],\"title\":\"The Hunt\",\"vote_average\":6.7,\"overview\":\"Twelve strangers wake up in a clearing. They don't know where they are—or how they got there. In the shadow of a dark internet conspiracy theory, ruthless elitists gather at a remote location to hunt humans for sport. But their master plan is about to be derailed when one of the hunted turns the tables on her pursuers.\",\"release_date\":\"2020-03-11\"},{\"popularity\":88.867,\"vote_count\":2431,\"video\":false,\"poster_path\":\"\\/i8QWXu6dGuTKKerJtnd0A4lUpbv.jpg\",\"id\":290859,\"adult\":false,\"backdrop_path\":\"\\/a6cDxdwaQIFjSkXf7uskg78ZyTq.jpg\",\"original_language\":\"en\",\"original_title\":\"Terminator: Dark Fate\",\"genre_ids\":[28,12,878],\"title\":\"Terminator: Dark Fate\",\"vote_average\":6.5,\"overview\":\"Decades after Sarah Connor prevented Judgment Day, a lethal new Terminator is sent to eliminate the future leader of the resistance. In a fight to save mankind, battle-hardened Sarah Connor teams up with an unexpected ally and an enhanced super soldier to stop the deadliest Terminator yet.\",\"release_date\":\"2019-10-23\"},{\"popularity\":87.941,\"vote_count\":7607,\"video\":false,\"poster_path\":\"\\/4q2NNj4S5dG2RLF9CpXsej7yXl.jpg\",\"id\":429617,\"adult\":false,\"backdrop_path\":\"\\/5myQbDzw3l8K9yofUXRJ4UTVgam.jpg\",\"original_language\":\"en\",\"original_title\":\"Spider-Man: Far from Home\",\"genre_ids\":[28,12,878],\"title\":\"Spider-Man: Far from Home\",\"vote_average\":7.5,\"overview\":\"Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.\",\"release_date\":\"2019-06-28\"},{\"popularity\":87.06,\"vote_count\":5418,\"video\":false,\"poster_path\":\"\\/iZf0KyrE25z1sage4SYFLCCrMi9.jpg\",\"id\":530915,\"adult\":false,\"backdrop_path\":\"\\/cqa3sa4c4jevgnEJwq3CMF8UfTG.jpg\",\"original_language\":\"en\",\"original_title\":\"1917\",\"genre_ids\":[28,18,36,10752],\"title\":\"1917\",\"vote_average\":7.9,\"overview\":\"At the height of the First World War, two young British soldiers must cross enemy territory and deliver a message that will stop a deadly attack on hundreds of soldiers.\",\"release_date\":\"2019-12-25\"},{\"popularity\":84.375,\"vote_count\":4910,\"video\":false,\"poster_path\":\"\\/db32LaOibwEliAmSL2jjDF6oDdj.jpg\",\"id\":181812,\"adult\":false,\"backdrop_path\":\"\\/jOzrELAzFxtMx2I4uDGHOotdfsS.jpg\",\"original_language\":\"en\",\"original_title\":\"Star Wars: The Rise of Skywalker\",\"genre_ids\":[28,12,878],\"title\":\"Star Wars: The Rise of Skywalker\",\"vote_average\":6.5,\"overview\":\"The surviving Resistance faces the First Order once again as the journey of Rey, Finn and Poe Dameron continues. With the power and knowledge of generations behind them, the final battle begins.\",\"release_date\":\"2019-12-18\"},{\"popularity\":81.891,\"vote_count\":18546,\"video\":false,\"poster_path\":\"\\/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg\",\"id\":299536,\"adult\":false,\"backdrop_path\":\"\\/lmZFxXgJE3vgrciwuDib0N8CfQo.jpg\",\"original_language\":\"en\",\"original_title\":\"Avengers: Infinity War\",\"genre_ids\":[28,12,878],\"title\":\"Avengers: Infinity War\",\"vote_average\":8.3,\"overview\":\"As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.\",\"release_date\":\"2018-04-25\"},{\"popularity\":81.606,\"vote_count\":1562,\"video\":false,\"poster_path\":\"\\/6KwrHucIE3CvNT7kTm2MAlZ4fYF.jpg\",\"id\":664413,\"adult\":false,\"backdrop_path\":\"\\/1M2Tku5XZYXOmAXM6LdRppC8RID.jpg\",\"original_language\":\"pl\",\"original_title\":\"365 dni\",\"genre_ids\":[18,10749],\"title\":\"365 Days\",\"vote_average\":7.2,\"overview\":\"Laura, in order to save her relationship from falling apart, goes to Sicily, where she meets Massimo. A dangerous man, the head of a mafia family, kidnaps her and gives her 365 days to fall in love with him.\",\"release_date\":\"2020-02-07\"},{\"popularity\":81.439,\"vote_count\":9909,\"video\":false,\"poster_path\":\"\\/xT98tLqatZPQApyRmlPL12LtiWp.jpg\",\"id\":122917,\"adult\":false,\"backdrop_path\":\"\\/bVmSXNgH1gpHYTDyF9Q826YwJT5.jpg\",\"original_language\":\"en\",\"original_title\":\"The Hobbit: The Battle of the Five Armies\",\"genre_ids\":[28,12,14],\"title\":\"The Hobbit: The Battle of the Five Armies\",\"vote_average\":7.3,\"overview\":\"Immediately after the events of The Desolation of Smaug, Bilbo and the dwarves try to defend Erebor's mountain of treasure from others who claim it: the men of the ruined Laketown and the elves of Mirkwood. Meanwhile an army of Orcs led by Azog the Defiler is marching on Erebor, fueled by the rise of the dark lord Sauron. Dwarves, elves and men must unite, and the hope for Middle-Earth falls into Bilbo's hands.\",\"release_date\":\"2014-12-10\"},{\"popularity\":79.483,\"vote_count\":13229,\"video\":false,\"poster_path\":\"\\/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg\",\"id\":475557,\"adult\":false,\"backdrop_path\":\"\\/f5F4cRhQdUbyVbB5lTNCwUzD6BP.jpg\",\"original_language\":\"en\",\"original_title\":\"Joker\",\"genre_ids\":[80,18,53],\"title\":\"Joker\",\"vote_average\":8.2,\"overview\":\"During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.\",\"release_date\":\"2019-10-02\"},{\"popularity\":76.806,\"vote_count\":1744,\"video\":false,\"poster_path\":\"\\/fapXd3v9qTcNBTm39ZC4KUVQDNf.jpg\",\"id\":423204,\"adult\":false,\"backdrop_path\":\"\\/k2WyDw2NTUIWnuEs5gT7wgrCQg6.jpg\",\"original_language\":\"en\",\"original_title\":\"Angel Has Fallen\",\"genre_ids\":[28,53],\"title\":\"Angel Has Fallen\",\"vote_average\":6.3,\"overview\":\"After a treacherous attack, Secret Service agent Mike Banning is charged with attempting to assassinate President Trumbull. Chased by his own colleagues and the FBI, Banning begins a race against the clock to clear his name.\",\"release_date\":\"2019-08-21\"},{\"popularity\":75.357,\"vote_count\":2646,\"video\":false,\"poster_path\":\"\\/8WUVHemHFH2ZIP6NWkwlHWsyrEL.jpg\",\"id\":338762,\"adult\":false,\"backdrop_path\":\"\\/ocUrMYbdjknu2TwzMHKT9PBBQRw.jpg\",\"original_language\":\"en\",\"original_title\":\"Bloodshot\",\"genre_ids\":[28,878],\"title\":\"Bloodshot\",\"vote_average\":7,\"overview\":\"After he and his wife are murdered, marine Ray Garrison is resurrected by a team of scientists. Enhanced with nanotechnology, he becomes a superhuman, biotech killing machine—'Bloodshot'. As Ray first trains with fellow super-soldiers, he cannot recall anything from his former life. But when his memories flood back and he remembers the man that killed both him and his wife, he breaks out of the facility to get revenge, only to discover that there's more to the conspiracy than he thought.\",\"release_date\":\"2020-03-05\"},{\"popularity\":73.381,\"vote_count\":2233,\"video\":false,\"poster_path\":\"\\/f4aul3FyD3jv3v4bul1IrkWZvzq.jpg\",\"id\":508439,\"adult\":false,\"backdrop_path\":\"\\/xFxk4vnirOtUxpOEWgA1MCRfy6J.jpg\",\"original_language\":\"en\",\"original_title\":\"Onward\",\"genre_ids\":[12,16,35,14,10751],\"title\":\"Onward\",\"vote_average\":7.9,\"overview\":\"In a suburban fantasy world, two teenage elf brothers embark on an extraordinary quest to discover if there is still a little magic left out there.\",\"release_date\":\"2020-02-29\"}]}";

    private QueryUtils(){}
    /**
     * Return a list of {@link Movie} objects that has been built up from
     * parsing a JSON response.
     */
    public static List<Movie> extractMovie() {

        // Create an empty ArrayList that we can start adding earthquakes to
        List<Movie> movies = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            //
            // build up a list of News objects with the corresponding data.
            JSONObject jsonObj = new JSONObject(SAMPLE_JSON_RESPONSE);
            JSONArray results = jsonObj.getJSONArray("results");


            // looping through All results
            for (int i = 0; i < results.length(); i++) {
                JSONObject currentMovie = results.getJSONObject(i);
                String title = currentMovie.getString("title");
                String releaseDateRaw = currentMovie.getString("release_date");
                String [] releaseDateArr = releaseDateRaw.split("-");
                String releaseDate = releaseDateArr[0];
                String description = currentMovie.getString("overview");
                String imageUrl = currentMovie.getString( "poster_path");
                String rating = currentMovie.getString("vote_average");
                Movie aMovie = new Movie("https://image.tmdb.org/t/p/w342/" + imageUrl, title, releaseDate, description, "120min", rating +"/10", "ansijnasidnsiajndijnsdisajndijasn");
                movies.add(aMovie);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of movies
        return movies;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    public static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            return null;
        }
        return url;
    }


    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }
    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
