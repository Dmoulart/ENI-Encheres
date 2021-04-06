/**
 * Pour le button popup
 */

var supprimerProfilPopup = document.getElementById('supprimerProfilPopup');
var overleyPopup = document.getElementId('overleyPopup');
var btnCClose = document.getElementId('btnClose');

btnPopup.addEventListener('click',openModal);
btnClose.addEventListener('click',closePopup);

function openModal() {
	overlayPopup.style.display = 'block';
}

function closePopup() {
	overlayPopup.style.display = 'none';
}

