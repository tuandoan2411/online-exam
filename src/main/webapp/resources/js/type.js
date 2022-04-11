const informations = document.getElementsByClassName("information");
for (let i = 0; i < informations.length; i++) {
    informations[i].addEventListener("click", function() {
        const selectedValue = informations[i].previousElementSibling.value;
        const level = informations[i].parentElement.parentElement.parentElement.id;
        if(level === "5") return;
        let splittedRootUrl = window.location.href.split("?");
        let url = "?";
        if (splittedRootUrl.length > 1) {
            let parameterArray = window.location.href.split("?")[1].split("&");
            for (let j = 0; j < level - 1; j++) {
                url += parameterArray[j] + "&";
            }
        }
        url += "level" + level + "=" + selectedValue;
        window.location.href = url;
    })
}

const adds = document.getElementsByClassName("add");
for (let i = 0; i < adds.length; i++) {
    adds[i].addEventListener("click", function() {
        adds[i].style.display = "none";
        const elementInputOfNewType = adds[i].nextElementSibling;
        elementInputOfNewType.style.display = "inline";
        elementInputOfNewType.focus();
        elementInputOfNewType.addEventListener("focusout", function() {
            elementInputOfNewType.value = "";
            elementInputOfNewType.style.display = "none";
            adds[i].style.display = "inline";
        });
        elementInputOfNewType.addEventListener("keyup", function(event) {
            if (event.key === "Enter") {
                const newType = elementInputOfNewType.value;
                if(newType === ""){
                    setMessage("Không đươc để dữ liệu trống");
                    showMessage();
                    return;
                }
                const level = elementInputOfNewType.parentElement.parentElement.id;
                let url = "/type/addUpdateType?currentLevel=" + level + "&value=" + newType + "&levelUrl=" + getLevelUrl(level);
                handler_add_update(level, url);
            }
        });
    })
}

const updates = document.getElementsByClassName("update");
for (let i = 0; i < updates.length; i++) {
    updates[i].addEventListener("click", function() {
        const containerModifyElement = updates[i].parentElement.previousElementSibling;
        const informationElement = containerModifyElement.previousElementSibling;
        informationElement.style.display = "none";
        containerModifyElement.style.display = "block";
        const modifyElement = containerModifyElement.firstElementChild;
        modifyElement.focus();
        modifyElement.addEventListener("focusout", function() {
            modifyElement.value = informationElement.textContent;
            containerModifyElement.style.display = "none";
            informationElement.style.display = "block";
        });
        modifyElement.addEventListener("keyup", function(event) {
            if (event.key === "Enter") {
                const newType = modifyElement.value;
                if(newType === ""){
                    setMessage("Không đươc để dữ liệu trống");
                    showMessage();
                    return;
                }
                const level = containerModifyElement.parentElement.parentElement.parentElement.id;
                const currentLevelId = informationElement.previousElementSibling.value;
                let url = "/type/addUpdateType?currentLevel=" + level + "&value=" + newType + "&currentLevelId=" + currentLevelId + "&levelUrl=" + getLevelUrl(level);
                handler_add_update(level, url);
            }
        });
    })
}

function handler_add_update(level, url) {
    if (level > 1) {
        let parameterArray = window.location.href.split("?")[1].split("&");
        for (let j = 0; j < parameterArray.length; j++) {
            const parameter = parameterArray[j].split("=");
            const name = parameter[0];
            if (name === "level" + (level - 1)) {
                url += "&previousLevelId=" + parameterArray[j].split("=")[1];
                break;
            }
        }
        // for (let k = 0; k < j; k++) {
        //     url += "&" + parameterArray[k];
        // }
    }
    console.log(url);
    window.location.href = url;
}

const deletes = document.getElementsByClassName("delete");
for (let i = 0; i < deletes.length; i++) {
    deletes[i].addEventListener("click", function() {
        const level = deletes[i].parentElement.parentElement.parentElement.parentElement.id;
        const currentLevelId = deletes[i].parentElement.parentElement.firstElementChild.value;
        console.log("/type/delete?currentLevel=" + level + "&currentLevelId=" + currentLevelId + "&levelUrl=" + getLevelUrl(level));
        window.location.href = "/type/delete?currentLevel=" + level + "&currentLevelId=" + currentLevelId + "&levelUrl=" + getLevelUrl(level);
    })
}

function getLevelUrl(level){
    let url = "";
    if(level > 1){
        let parameterArray = window.location.href.split("?")[1].split("&");
        for (let j = 0; j < level - 1; j++) {
            if(j === level - 2){
                url += parameterArray[j];
                break;
            }
            url += parameterArray[j] + "and";
        }
    }
    console.log(url);
    return url;
}

function setMessage(message) {
    document.getElementById("message").innerText = message;
}

function showMessage() {
    document.getElementById("modal").style.display = "block";
}

function closeMessage() {
    document.getElementById("modal").style.display = "none";
}
