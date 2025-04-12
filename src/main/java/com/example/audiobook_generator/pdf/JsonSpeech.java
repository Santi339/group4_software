package src.main.java.com.example.audiobook_generator.pdf; //change later

import marytts.util.data.audio.AudioPlayer;
import marytts.modules.synthesis.Voice;
import marytts.LocalMaryInterface;
import marytts.exceptions.MaryConfigurationException;

import org.json.JSONObject;
import org.json.JSONArray;

import java.io.ByteArrayInputStream;
import java.util.List;
//import Fuzzy Wuzzy
import java.util.Scanner;

public class JsonSpeech {
 
    private String jsonString;
    private LocalMaryInterface marytts;

    public JsonObj(String jsonString) {
        try{
        this.jsonString = jsonString;
        LocalMaryInterface pdfbook = new LocalMaryInterface();
        }
        catch (Exception e){
            System.out.println("Error downloading contents: " + e.getMessage());
        }
    }

    public List<String> getVoicesAvail() {
        List<String> v_list = new ArrayList<>();
        try{
            voices = marytts.getAvailableVoices();
            for voice in voices:
                if (voice.contains(("en")) || voice.contains("us-en")):
                    v_list.add(voice);
            return v_list;
        }
        catch (Exception e){
            System.out.println("Error retrieving voices: " + e.getMessage());
        }
    }

    public void getVoiceChoices() {
        List<String> v_list = getVoicesAvail();
        try{
            String msg = "Here are the different voices available for audio translation: \n";
            System.err.println(msg);
            int i =1
            for item in v_list:
                System.out.println(i, ". ", item);
                i=i+1;
        }
        catch (Exception e){
            System.out.println("Error retrieving voices: " + e.getMessage());
        }

    public void setVoice(String input) {
        try{
            List<String> v_list = getVoicesAvail();
            getVoiceChoices(); //prints voice choices
            String msg = "Please choose which voice you'd like your audio file to be made with. Enter it below: \n";
            Scanner VoiceChoice = new Scanner(System.in);
            System.out.println(msg);
            String input = VoiceChoice.nextLine()

            String msg2="We detected that you chose: "
            #
        }
    }

    public void ChooseVoice() {
       
        }
    
    public void setVoice(String voiceName) {
        Voice voice = marytts.getVoice(voiceName);
        if (voice != null) {
            marytts.setVoice(voice);
        } else {
            System.out.println("Voice not found: " + voiceName);
        }
    }
    public void setRate(int rate) {
        marytts.setRate(rate);
    }
    public void setPitch(float pitch) {
        marytts.setPitch(pitch);
    }
    public void setVolume(float volume) {
        marytts.setVolume(volume);
    }
    public void setAudioFormat(String format) {
        marytts.setAudioFormat(format);
    }
    public void setAudioFile(String filePath) {
        marytts.setAudioFile(filePath);
    }
    public void setAudioPlayer(AudioPlayer audioPlayer) {
        this.marytts.setAudioPlayer(audioPlayer);
    }
   