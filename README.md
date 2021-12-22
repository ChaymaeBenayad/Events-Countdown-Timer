# Events-Countdown-Timer

Development of a mobile application that provides a continuous decrementing display of months, days, hours, minutes, and seconds of each event entered by the user. It is developed using Android Studio, Java, XML, MySQL.

<h2>L’interface d’accueil :</h2>

L’interface d’accueil est la première interface qui va apparaitre dès que l’utilisateur lance l’application, cette interface comporte un bouton « Commencer », qui va nous rediriger vers l’interface de l’affichage des événements existants.

![image](https://user-images.githubusercontent.com/78702422/147033062-c085c63b-4f1e-4373-ae9a-2231a95e8ce0.png)

<h2>La liste des événements :</h2>

Les noms des événements qui existent dans la base de données de l'application sont affichés sous forme d'une liste.

![image](https://user-images.githubusercontent.com/78702422/147033236-1088a23e-bfb4-4f4c-bc2c-1ba1d5aed3b7.png)

<h2>Ajouter un événement :</h2>

Cette interface apparaît lorsque on clique sur l’icône Plus, l'utilisateur doit saisir le titre de l'événement et choisir sa date à partir du calendrier.

![image](https://user-images.githubusercontent.com/78702422/147033369-b239814a-2f68-43ee-920b-0e7616265790.png) ![image](https://user-images.githubusercontent.com/78702422/147033409-48bdba51-9487-48c8-8723-0efb0fb210f3.png)

Le remplissage des deux champs est obligatoire, sinon un message d’avertissement s’affiche.

![image](https://user-images.githubusercontent.com/78702422/147033480-c5372746-76c4-44d9-9771-3e63745d57b4.png)

Après le rafraîchissement de la page, on remarque la présence de l’évenement crée.

![image](https://user-images.githubusercontent.com/78702422/147033541-8e17c160-9487-4653-8fe6-1e9dc3bfc5ce.png)

<h2>Menu des actions :</h2>

En sélectionnant un événement, un menu d'actions s'affiche. Il contient trois actions possibles :  
<li>Afficher</li>
<li>Modifier</li> 
<li>Supprimer</li>

![image](https://user-images.githubusercontent.com/78702422/147033747-40bc0633-fcfb-49fb-a299-cef46e084665.png)

<h2>Modifier un événement :</h2>

Cette interface apparaît lorsqu'on clique sur l'option "Modifier" de l'événement sélectionné de la liste précédente. Les informations de l'événement sont initialisées au début pour faciliter la tâche de la modification.

<h2>Supprimer un événement :</h2>

En cliquant sur l'option "Supprimer" de l'événement sélectionné de la liste précédente, Une alerte s’affiche pour demander à l'utilisateur la confirmation de cette action.

![image](https://user-images.githubusercontent.com/78702422/147034602-39cb8515-06c0-4170-bc4a-cb0c3138571c.png)

<h2>Afficher un événement :</h2>

Cette interface apparaît lorsqu'on clique sur l'option "Afficher" de l'événement sélectionné de la liste précédente. Elle contient le nom de l'événement, sa date et le temps qui lui reste.

![image](https://user-images.githubusercontent.com/78702422/147033956-25dc8b6b-aaa2-4494-93e3-f01d3de2b92f.png)

On remarque que l’événement n’existe plus dans la liste après la suppression.

![image](https://user-images.githubusercontent.com/78702422/147034672-63a64de5-d52c-44aa-9fcc-80e79b19aa34.png)
