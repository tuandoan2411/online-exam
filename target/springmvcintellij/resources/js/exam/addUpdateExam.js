const containerInformationElements = document.getElementsByClassName("container-information");
for(const containerInformationElement of containerInformationElements){
    containerInformationElement.addEventListener("click", function (){
        const informationElement = containerInformationElement.firstElementChild;
        const level = containerInformationElement.parentElement.parentElement.firstElementChild.id;
        let url = "/exam/addExam?"
        let isSpecialCase = false;
        if(level === "4" && document.getElementById("information1").value === "Đề kiểm tra"){
            isSpecialCase = true;
        }
        if(informationElement.style.fontWeight === "bold" || isSpecialCase){
            let typeId = "";
            for(let i = 1; i < level; i++){
                typeId += document.getElementById(i).value + "-";
            }
            typeId += containerInformationElement.previousElementSibling.value;
            let typeInformation = "";
            for(let i = 1; i < level; i++){
                typeInformation += document.getElementById("information" + i).value + " - ";
            }
            typeInformation += informationElement.textContent;
            url += "typeId=" + typeId + "&typeInformation=" + typeInformation;

        }else{
            for(let i = 1; i < level; i++){
                url += "level" + i + "=" + document.getElementById(i).value + "&";
            }
            url += "level" + level + "=" + containerInformationElement.previousElementSibling.value;
        }
        window.location.href = url;
    })
}

function showOrHiddenType() {
    const containerType = document.getElementById("container-type");
    if (containerType.style.display === "none") {
        containerType.style.display = "block";
    } else {
        containerType.style.display = "none";
    }
}

function addNewSentence() {
    document.getElementById("action").value = "ADD_NEW_QUESTION";
    if(document.getElementById("is-update").value === ""){
        document.getElementById("is-update").value = "false";
    }
}

function saveExam() {
    document.getElementById("action").value = "SAVE_EXAM";
    if(document.getElementById("is-update").value === ""){
        document.getElementById("is-update").value = "false";
    }
}

function deleteSentence(object) {
    console.log(object.id);
    let url = "/exam/deleteSentenceInSession?sentence=" + object.id;
    if(document.getElementById("is-update").value === "true"){
        url += "&is-update=true";
    }
    window.location.href = url;
}