import fs from "fs";
import path from "path";
import { JigsawStack } from "jigsawstack";


const apiKey = "sk_0bb019834613159e8ae9fe1446187030c01ff05d8bc73596aca31e75482397a8aacf774046bad65e32d4d55416f3802c507579f95633b611a0595f93e0292314024r5B3QOuQ6vDXWnM1i3";
const jigsaw = JigsawStack({ apiKey });

//  File paths
const outputText = path.resolve("output.txt");
const outputMP3 = path.resolve("output.mp3");

//  Public direct file URL (PDF or image)
const fileURL = "https://nlsblog.org/wp-content/uploads/2020/06/true-pdf-sample-1.pdf";

// Clean and limit extracted text
function cleanText(text) {
  return text
    .replace(/\n+/g, " ")              // remove line breaks
    .replace(/[^\x00-\x7F]/g, "")      // remove non-ASCII
    .trim()
    .slice(0, 500);                    // limit to 500 characters for TTS
}

//  Step 1: Extract text from a PDF/image URL via vOCR
async function extractWithVOCRFromURL(url) {
  try {
    const result = await jigsaw.vision.vocr({ url });

    const text = typeof result === "string"
      ? result
      : Object.values(result).join("\n");

    fs.writeFileSync(outputText, text);
    console.log(`Extracted text saved to ${outputText}`);

    const cleaned = cleanText(text);
    if (!cleaned) throw new Error("Cleaned text is empty or invalid.");
    return cleaned;
  } catch (err) {
    console.error("vOCR Error:", err.message);
  }
}

//  Step 2: Convert cleaned text to speech (TTS)
async function convertTextToSpeech(text) {
  try {
    console.log(" Sending to TTS:", text);

    const audioResponse = await jigsaw.audio.text_to_speech({
      text,
      voice: "en-US-female-27"
    });

    const audioBuffer = await audioResponse.buffer();
    fs.writeFileSync(outputMP3, Buffer.from(audioBuffer));
    console.log(` Audio saved to ${outputMP3}`);
  } catch (err) {
    console.error("❌ TTS Error:", err.message);
  }
}

//  Run the full pipeline
(async () => {
  const cleanedText = await extractWithVOCRFromURL(fileURL);
  if (cleanedText) {
    await convertTextToSpeech(cleanedText);
  }
})();
