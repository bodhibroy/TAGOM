// This JavaScript fetches the audio files from the server based on the difference in semi-tones as requested by the user.
//
//
window.flag;
function startgame(){
    alert("Please put on your headphones and press Ok");
    document.getElementById("t1").style.display="block"; 
    document.getElementById("replay").style.display="block"; 
    document.getElementById("music").style.display="block"; 
    document.getElementById("b1").style.display="none"; 
    
    var r1,r2,r3,str1,str2;

    var range=["C3" , "C%233" , "D3" , "D%233" , "E3", "F3" ,
              "F%233" , "G3" , "G%233" ,"A3" , "A%233" , "B3" , "C4" , "C%234" , "D4" ,
              "D%234" , "E4" , "F4" , "F%234" , "G4" , "G%234" , "A4" , "A%234" , "B4" ,
              "C5" , "C%235" , "D5" , "D%235" , "E5" , "F5" , "F%235" , "G5" , "G%235" ,
              "A5" , "A%235" , "B5"];
    var len=range.length; 
    r1=Math.floor(Math.random()*len);
    diff=parseInt(document.getElementById("differ").value); 
    r2=r1-diff; 
    r3=r1+diff; 
    var randomnumber=Math.floor(Math.random()*2)+2;
    //alert(r1+" "+r2+" "+r3);
//fetch Array[r1].ogg
    
    str1="./audio_samples/"+range[r1]+".ogg";
    
    if(randomnumber==2){
	if(r2>=0){
            str2="./audio_samples/"+range[2]+".ogg";
	flag=0; // means that the first tone is higher than the second one
       }
    }
    else{
	if(r3<len){
        //fetch Array[r3].ogg
    
       str2="./audio_samples/"+range[r3]+".ogg";
	flag=1; // means that the first tone is lower than the second one
        }
    }//end of if-else
    // insert the proper music links in HTML
    document.getElementById('m1').href=str1;
    document.getElementById('m2').href=str2;
}//end of startgame()

