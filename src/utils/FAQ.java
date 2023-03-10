package utils;

import java.util.HashMap;
import java.util.Map;

public class FAQ {
//	private Map<String, String> FAQ_en;
	
	public static final String ES="es";
	public static final String EN="en";
	
	private static Map<String, String> buildFaq_en() {
		Map<String, String> map = new HashMap<>();
		map.put(
				"What features does the hotel management app have?",
				"The app includes the creation and updating of users and rooms, as well as booking creation functionality, to create a booking, navigate to the \"Bookings\" section in the left hand navigation panel and click on the \"create booking\" button and introduce the required data."
				);
		map.put(
				"Can I manage multiple hotels with the app?",
				"That functionality is not yet implemented."
				);
		map.put(
				"How does the app handle guest check-ins and check-outs?",
				"The app is currently only designed for working with the database and creating bookings with specified dates, the check-in and check-out process is handled in person in the hotel."
				);
		map.put(
				"How can I manage room availability and pricing with the app?",
				"The number of rooms of a given designation as well as the price is easily accessed from the \"Rooms\" button in the left hand navigation panel, in the main table."
				);
		map.put(
				"Does the app have any integrations with other hotel management systems?",
				"As it currently stands it is built as a standalone app."
				);
		map.put(
				"Can I access the app from anywhere, or just from a specific location?",
				"This specific app is designed for desktop usage, however a mobile version of it exists for Android users."
				);
		map.put(
				"Is there any training available to help me get started with the app?",
				"The training is handled through the company responsible for managing the hotel, please do ask any manager in your company for the training you are seeking."
				);
		map.put(
				"How does the app handle customer support and technical issues?",
				"The app hasn't implemented any CRM like features, the interaction with the user is handled by the hotel workers or through the phone."
				);
		
		return map;
	}
	
	private static Map<String, String> buildFaq_es() {
		Map<String, String> map = new HashMap<>();
		map.put(
				"¿Qué funcionalidades tiene la app de gestión hotelera?",
				"La aplicación incluye la creación y actualización de usuarios y habitaciones, así como la funcionalidad de creación de reservas, para crear una reserva, vaya a la sección \"Reservas\" en el panel de navegación de la izquierda y haga clic en el botón \"crear reserva\" e introduzca la información requerida."
				);
		map.put(
				"¿Puedo gestionar varios hoteles con la aplicación?",
				"Esa funcionalidad aún no está implementada."
				);
		map.put(
				"¿Cómo maneja la aplicación los registros de entrada y salida de los huéspedes?",
				"La aplicación actualmente solo está diseñada para trabajar con la base de datos y crear reservas con fechas específicas, el proceso de check-in y check-out se maneja personalmente en el hotel."
				);
		map.put(
				"¿Cómo puedo administrar la disponibilidad de habitaciones y los precios con la aplicación?",
				"Se puede acceder fácilmente al número de habitaciones de una designación determinada, así como al precio, desde el botón \"Habitaciones\" en el panel de navegación izquierdo, en la tabla principal."
				);
		map.put(
				"¿La aplicación tiene alguna integración con otros sistemas de gestión hotelera?",
				"Tal como está actualmente, está construida como una aplicación independiente."
				);
		map.put(
				"¿Puedo acceder a la aplicación desde cualquier lugar o solo desde una ubicación específica?",
				"Esta aplicación específica está diseñada para uso de escritorio, sin embargo, existe una versión móvil para usuarios de Android."
				);
		map.put(
				"¿Hay alguna capacitación disponible para ayudarme a comenzar con la aplicación?",
				"La formación se lleva a cabo a través de la empresa responsable de la gestión del hotel, por favor pregunte a cualquier gerente de su empresa por la formación que está buscando."
				);
		map.put(
				"¿Cómo maneja la aplicación la atención al cliente y los problemas técnicos?",
				"La aplicación no ha implementado ninguna función similar a CRM, la interacción con el usuario la manejan los trabajadores del hotel o a través del teléfono."
				);
		
		return map;
	}
	
	/**
	 * Method for getting FAQ list in the selected language
	 * 
	 * @param lang Language, use class constants EN and ES
	 * @return Map with FAQ, null if language isn't valid
	 */
	public static Map<String, String> getMap(String lang) {
		if (lang.equals(EN)) {
			return buildFaq_en();
		} else if (lang.equals(ES)) {
			return buildFaq_es();
		} else return null;
	}
}
