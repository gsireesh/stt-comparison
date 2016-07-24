# stt-comparison
This is a project designed to compare speech-to-text transcription services. At the moment, it only supports IBM Watson's services, though this will be extended to cover Google and Microsoft services soon.

I meant this project to help me gradually learn about Scala development, so hopefully it will get less Java-inflected over time.

## Comparison Methodology

This project uses the [Word Error Rate](https://en.wikipedia.org/wiki/Word_error_rate) (WER) metric to measure the accuracy of the services. This metric is similar to the Levenshtein distance between two strings, albeit at the word level.

The test set is the [The Pacific Northwest/Northern Cities (PN/NC) corpus](https://depts.washington.edu/phonlab/resources/pnnc/). This corpus contains readings of the IEEE "[Harvard Sentences](https://en.wikipedia.org/wiki/Harvard_sentences)", a collection of sentences that has been used to test the fidelity of telephony systems. The files are not included in the repository, but can be fetched by running the  `get_audio_files.sh` script in the `src/main/resources` directory, which will create the directory that the code expects.

Currently, the `App` object takes the first sentence from the corpus and runs the transcription against the Watson service. It then outputs a diff view of the service's response as compared to the expected response, and the WER.

## Running the code

- Set up an account on bluemix and an instance of the Watson STT service
- Create a valid `config.properties` file - rename config.properties.example, and supply a username and password for the Watson services, after having set them up to be used.
- run the `get_audio_files` script to pull down the audio files used for measurement.
- run a Maven build (`mvn install`) to pull down dependencies and compile
- run `mvn exec:java`