<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>ENI-ENCHERES Page d'Accueil</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;400;500;600;700&family=Quicksand:wght@300;400;500;600;700&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="CSS/general.css">
    <link rel="stylesheet" href="CSS/index.css">
    <link rel="stylesheet" href="CSS/theme.css">
</head>

<body class ="Page">

    <div class="NavbarContainer">
        <nav class="Navbar">
            <div class="Logo"><a class="link" href="./IndexServlet">ENI-Enchères</a></div>
          <ul class="Menu">
     		<c:choose>
				
     			<c:when test="${utilisateurEnSession!=null && utilisateurEnSession.administrateur!=false}">  
		            	<li class="Menu_item"><a class="link" href="./EnchereServlet">Enchères</a></li>
		                <li class="Menu_item"><a class="link" href="./VenteServlet">Vendre un article</a></li>
		                <li class="Menu_item"><a class="link" href="./ProfilServlet">Mon Profil</a></li>
		                <li class="Menu_item"><a href="./DeconnectionServlet" class="link">Se deconnecter</a></li>
		        </c:when>
		        <c:when test="${utilisateurEnSession!=null && utilisateurEnSession.administrateur!=true}">  
     					<li class="Menu_item"><a class="link" href="./AdminServlet">Administration</a></li>
		            	<li class="Menu_item"><a class="link" href="./EnchereServlet">Enchères</a></li>
		                <li class="Menu_item"><a class="link" href="./VenteServlet">Vendre un article</a></li>
		                <li class="Menu_item"><a class="link" href="./ProfilServlet">Mon Profil</a></li>
		                <li class="Menu_item"><a href="./DeconnectionServlet" class="link">Se deconnecter</a></li>
		        </c:when>
		        <c:otherwise>
		        		<li class="Menu_item"><a href="./InscriptionServlet" class="link">S'inscrire</a></li>
		        		<li class="Menu_item"><a href="./ConnectionServlet" class="link">Se connecter</a></li>
		        </c:otherwise>
		        
		     </c:choose>
  			</ul>
        </nav>
    </div>

    <div class="Page-TitleContainer"><h1>Liste des enchères</h1></div>
    
    
    <c:choose>
    	<c:when test="${utilisateurEnSession!=null}">
   			 <div class="Page-TitleContainer"><h2>Bonjour ${utilisateurEnSession.prenom} ${utilisateurEnSession.nom}</h2></div>
    	</c:when>
	</c:choose>
	<div class="SearchContainer">
	    <form class="SearchContainer" method="POST" action="./IndexServlet">
	        <div class ="Search_paramsContainer">
	            <div class="Search_filter" id="filters">Filtres : </div>
	            <div class="Search_barContainer">
	                <input type="text" class="Search_bar"name="searchContent" value="${motsRecherches}">
	                <div class="Search_categoryContainer">
	                    <label>Catégorie</label>
		                    <select class="Search_select-category" name="selectCategorie">
		                    	<option id="Toutes" <c:if test="${empty categorieSelectionnee}">selected</c:if>>Toutes</option>
		                    		<c:forEach var="categorie" items="${categories}">
											<option 
												id="${categorie.libelle}" 
												<c:if test="${categorieSelectionnee eq categorie.libelle}">
												selected</c:if>>
												 ${categorie.libelle}
											 </option>
									</c:forEach>
		                    </select>
	                    </div>
	                </div>
	            </div>
	        <div class="Search_buttonContainer">
	            <button type="submit" class="Search_button">Rechercher</button>
	        </div>
	    
	    
	    <!-- OPTIONS EN MODE CONNECTE -->
		<c:choose>
	    	<c:when test="${utilisateurEnSession!=null}">
	   			<div class="SearchOptionsContainer">
	   			
					<div class="OptionContainer" id="achatsOption">
						<div class="SearchOptionType">
							<input type="radio" name="achats" id="achatsRadio" <c:if test="${param.achats eq 'on' ||  defaultSearchParam eq 'on'}">checked</c:if>>
							<label for="achats">Achats</label>
						</div>
							<div class="SearchOption" >
								<input type="checkbox" name="enchereOuvertes" id="enchereOuvertes" <c:if test="${param.enchereOuvertes eq 'on'}">checked</c:if>>
								<label for="achats">enchères ouvertes</label>
							</div>
							
							<div class="SearchOption">
								<input type="checkbox" name="mesEncheres" id="mesEncheres" <c:if test="${param.mesEncheres eq 'on'}">checked</c:if>>
								<label for="achats">mes enchères</label>							
							</div>
							
							<div class="SearchOption">
								<input type="checkbox" name="mesEncheresRemportees" id="mesEncheresRemportees" <c:if test="${param.mesEncheresRemportees eq 'on'}">checked</c:if>>
								<label for="achats">mes enchères remportées</label>							
							</div>
					</div>
					
					<div class="OptionContainer"  id="ventesOption">
					
						<div class="SearchOptionType">
							<input type="radio" name="mesVentes" id="mesVentesRadio" <c:if test="${param.mesVentes eq 'on'}">checked</c:if>>
							<label for="achats">Mes ventes</label>
						</div>
						
							<div class="SearchOption">
								<input type="checkbox" name="ventesEnCours" id="ventesEnCours" <c:if test="${param.ventesEnCours eq 'on'}">checked</c:if>>
								<label for="achats">mes ventes en cours</label>
							</div>
							
							<div class="SearchOption">
								<input type="checkbox" name="ventesNonDebutees" id="ventesNonDebutees" <c:if test="${param.ventesNonDebutees eq 'on'}">checked</c:if>>
								<label for="achats">ventes non débutées</label>
							</div>
							
							<div class="SearchOption">							
								<input type="checkbox" name="ventesTerminees" id="ventesTerminees" <c:if test="${param.ventesTerminees eq 'on'}">checked</c:if>>
								<label for="achats">ventes terminées</label>
							</div>
					</div>
				</div>
	    	</c:when>
		</c:choose>
		
		</form>
	</div>
	
    <section class="Articles-Section">
		<c:forEach var="article" items="${articles}">
				<c:url value="/ArticleServlet" var="url">
	  				<c:param name="articleId" value="${article.id}" />
				</c:url>
					<div class="ArticleContainer">
			           <img src="" class="Article_image">
			            <div class="Article_informationsContainer">
			              <c:choose> 
				              	<c:when test="${utilisateurEnSession!=null}"> 
				              		<div class="Article_name"><a href="${url}">${article.nom}</a></div>
				              	</c:when>
				              	
				              	<c:otherwise>
				              		<div class="Article_name">${article.nom}</div>
				              	</c:otherwise>
			              	</c:choose>
			                <div class="Article_properties prix">Prix : ${article.prixVente}</div>
			                <div class="Article_properties finEnchere">Fin de l'enchère : ${article.finEncheres.toString()}</div>
			                <div class="Article_properties retrait">Retrait : ${article.retrait.rue}</div>
			                <c:url value="/ProfilUtilisateurServlet" var="urlUt">
	  							<c:param name="utilisateurId" value="${article.vendeur.id}" />
							</c:url>
							
							<c:choose> 
				              	<c:when test="${utilisateurEnSession!=null}"> 
				              		<div class="Article_properties vendeur"><a href="${urlUt}">Vendeur:  ${article.vendeur.nom}</a></div>
				              	</c:when>
				              	<c:otherwise>
				              		<div class="Article_properties vendeur">Vendeur:  ${article.vendeur.nom}</div>
				              	</c:otherwise>
			              	</c:choose>		
			              		              
			            </div>
			        </div>
		</c:forEach>
	</section>
<script type="text/javascript">

let radioAchat = document.getElementById("achatsRadio");
let radioMesVentes = document.getElementById("mesVentesRadio");

let achatNodes = Array.from(document.getElementById("achatsOption").getElementsByTagName('INPUT'));
let ventesNodes = Array.from(document.getElementById("ventesOption").getElementsByTagName('INPUT'));

if(radioAchat.checked === true){
	ventesNodes.forEach((n,i) =>  {
		if(i>0){
			n.disabled = true;
			n.checked = false;
		}
	});
}

if(radioMesVentes.checked === true){
	achatNodes.forEach((n,i) =>  {
		if(i>0){
			n.disabled = true;
			n.checked = false;
		}
	});
}

radioAchat.onclick = () => {
	radioAchat.checked = true;
	radioMesVentes.checked = false;
	
	ventesNodes.forEach((n,i) =>  {
		if(i>0){
			n.checked = false;
			n.disabled = true;
		}
	});
	achatNodes.forEach(a => a.disabled = false);
}

radioMesVentes.onclick = () => {
	radioMesVentes.checked = true;
	radioAchat.checked = false;
	
	achatNodes.forEach((n,i) =>  {
		if(i>0){
			n.disabled = true;
			n.checked = false;
		}
	});
	ventesNodes.forEach(a => a.disabled = false);
	
	
}

</script>
</body>
</html>