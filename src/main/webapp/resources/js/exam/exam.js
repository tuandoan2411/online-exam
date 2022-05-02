const updates = document.getElementsByClassName("update");
for (let i = 0; i < updates.length; i++) {
    updates[i].addEventListener("click", function() {
        const exam_id = updates[i].parentElement.id;
        window.location.href = "/exam/showExamForUpdate?exam-id=" + exam_id;
    })
}

const deletes = document.getElementsByClassName("delete");
for (let i = 0; i < deletes.length; i++) {
    deletes[i].addEventListener("click", function() {
        const exam_id = deletes[i].parentElement.id;
        window.location.href = "/exam/delete?exam-id=" + exam_id;
    })
}

function addExam() {
    window.location.href = "/exam/addExam";
}