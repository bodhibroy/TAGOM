// This function decided whether the user has given a correct answer
// or not

function evaluate_User(user_val)
{
    s=parseInt(document.getElementById("point").innerHTML);
    t=parseInt(document.getElementById("try").innerHTML);
    if(window.flag==user_val)
    {
	alert("Correct!");
	s+=10; // increase score by 10
	t+=1;  // increase number of tries by 1
	document.getElementById("point").innerHTML=s;
    }
    else
    {
	alert("Wrong!");
	t+=1; // increase number of tries by 1
    }
    document.getElementById("t1").style.display="none"; 
    document.getElementById("music").style.display="none"; 
    document.getElementById("replay").style.display="block"; 

    document.getElementById("try").innerHTML=t;
}
