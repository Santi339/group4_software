package com.example.audiobook_generator.pdf.PdfController;

import marytts.util.data.audio.AudioPlayer; //check
import marytts.modules.synthesis.Voice; //check
import marytts.LocalMaryInterface; //check
import marytts.exceptions.MaryConfigurationException; //check

import org.json.JSONObject; //check
import java.util.Iterator; //Check

import java.util.ArrayList; //check
import java.util.List; //check
import me.xdrop.fuzzywuzzy.FuzzySearch; //check
import java.util.Scanner; //check

import java.io.File; //check
import java.io.IOException; //check

import javazoom.jl.converter.Converter; //check

import javax.sound.sampled.AudioFileFormat; //check
import javax.sound.sampled.AudioInputStream; //check
import javax.sound.sampled.AudioSystem; //check



public class JsonSpeech { //Class that changes txt from Json object to audio
 
    private JSONObject json; //idk if i need this

    public String getJsonName(JSONObject json) { //returns json object name
        this.json = json;
        Iterator<String> json_keys = json.keys();
        String text = json_keys.next(); //only getting first key AKA book text
        Object firstValueFromObject = jsonObject.get(firstKey);
        String firstValue = firstValueFromObject.toString();
        return firstValue
        }

    public String getJsonTxt(JSONObject json) { //returns json object text
        
        this.json = json;
        Iterator<String> json_keys = json.keys();
        String text = json_keys.next().toString(); //only getting first key AKA book text
        return text;
        
        }

    public List<String> getVoicesAvail() { //returns list of voices available
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

    public void getVoiceChoices() { //prints voice choices
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

    public String setVoice() { //user chooses voice from selection available
        try{
            List<String> v_list = getVoicesAvail(); //saving voices available
            getVoiceChoices(); //prints voice choices
            String msg = "Please choose which voice you'd like your audio file to be made with. Enter it below: \n";
            Scanner VoiceChoice = new Scanner(System.in);
            System.out.println(msg);
            String input = VoiceChoice.nextLine(); //getting user input/voice request

            String msg2="We detected that you chose: ";
            int ratio;
            int biggest_num = 0;
            String best_match = "";
            for item in v_list{
                ratio = FuzzySearch.ratio(item, input);
                if (biggest_num < ratio){
                    biggest_num = ratio;
                    best_match = item;
                }
            }
            System.out.println(msg2, best_match, ". Is this correct?");
            System.out.println("Please enter 'yes' or 'no': ");
            Scanner Confirmation = new Scanner(System.in);
            String input2 = Confirmation.nextLine(); //getting user input/voice request
            if (input2.equalsIgnoreCase("no") || input2.equalsIgnoreCase("n")){
                setVoice();
            }
            else {
                System.out.println("Saving "+ best_match + " as your voice choice.");
                return best_match;
            }
        }
          
        catch { (Exception e){
            System.out.println("Error during processing: " + e.getMessage());
        }
        }
    }
   
    public AudioInputStream makeAudio(String voice_choice, JSONObject json) { //Beginning of audio constructor
        try{
            text = getJsonTxt(json) //only getting first key AKA book text
           
            this.voice_choice = voice_choice; //setting voice choice
           
            LocalMaryInterface pdfbook = new LocalMaryInterface();
            marytts.setVoice(voice_choice); //sets voice choice
            //pitch/augmentations go here
            AudioInputStream Txt_to_Audio = marytts.generateAudio(text);
            return Txt_to_Audio;
        }
        catch (Exception e){
            System.out.println("Error during processing: " + e.getMessage());
        }   
    }

    public AudioFileFormat.Type.WAVE Download_WAV_File(AudioInputStream audio, JSONObject json) { //returns wav voice file
        try{
            firstValue = getJsonName(json); //getting json name
            AudioSystem.write(audio, AudioFileFormat.Type.WAVE, new File((firstValue.trim()), ".wav"));
            System.out.println("WAV file saved: " + (firstValue.trim()+ ".wav").getAbsolutePath());
        }
    }

    public Converter Wav_to_MP3(AudioFileFormat.Type.WAVE wav_file, JSONObject json) { //returns mp3 file
        try{
            Converter converter = new Converter();
            String name = getJsonName(json) + ".mp3";
            converter.convert(wav_file, name)
            if (new File(name).exists()) {
                System.out.println("MP3 file saved: " + name.getAbsolutePath());
        } 
        catch (Exception e) {
            System.out.println("Error creating your mp3"+ e.getMessage());
        }
    }

 

    //optional additional fancy add-ons for audio generation-- I dont want to do this- Lizzy/Elizabeth
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
   