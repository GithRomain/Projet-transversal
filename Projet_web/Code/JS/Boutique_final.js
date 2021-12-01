var slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
    showSlides(slideIndex += n);
}

function currentSlide(n) {
    showSlides(slideIndex = n);
}
function showSlides(n) {
    var i;
    var slides = document.getElementsByClassName("produitSlides");
    var dots = document.getElementsByClassName("dot");
    if (n > slides.length) {
        slideIndex = 1
    }
    if (n < 1) {
        slideIndex = slides.length
    }
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[slideIndex - 1].style.display = "block";
    dots[slideIndex - 1].className += " active";
}

var infos = document.forms.commande;

function ajouter(){
    function handleForm(event) { event.preventDefault(); }
    
    infos.addEventListener('submit', handleForm);

    let tr = document.createElement('tr');

    var name = document.getElementsByClassName("produit-name").item(slideIndex-1);
    let td = document.createElement('td');
    td.textContent = name.textContent;
    tr.appendChild(td);
    console.log(name.textContent);
    
    for (i = 0; i < 9; i++){
        let td = document.createElement('td');
        td.textContent = infos[i].value;
        tr.appendChild(td);
    }
    let table = document.getElementsByName("affichage")[0];
    table.appendChild(tr);
}

/*
function supprimer(){
    let table = document.getElementsByName("affichage")[0];
    console.log(table)
    let tr = document.getElementsByName("tr")[1];
    console.log(tr);
    for (i = 0; i < 9; i++){
        tr.removeChild(td);
    }
    table.removeChild(tr);
}
*/