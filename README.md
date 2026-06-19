# Hotel Pattern Masters

Integrantes del grupo: Leandro Corti, Agostina Sol Lopez y Thiago Minian

Gestionar un hotel implica coordinar muchas piezas al mismo tiempo: saber qué habitaciones están libres, qué huéspedes tienen reservas, qué servicios pidieron, cuánto deben pagar y cómo avisarles si algo cambia. Cuando todo eso se maneja de forma manual o con herramientas separadas, los errores son inevitables: se asigna una habitación que ya estaba ocupada, un huésped no se entera de que se liberó el lugar que estaba esperando, o el costo final se calcula mal porque no se tuvieron en cuenta los descuentos.
El problema central no es solo la falta de herramientas, sino la falta de integración. Cada área trabaja con su propia información, y cuando ocurre un cambio en una parte, las demás no se enteran a tiempo.

Instrucciones para ejecutar el proyecto:  

cd C:\Users\thiag\OneDrive\Escritorio\Documentos\GitHub\Pattern-Masters-The-Rebirth\hotel-app\backend
node server.js  

Y con esto debería aparecer así:

◇ injected env (6) from .env // tip: ⌘ multiple files { path: ['.env.local', '.env'] }
🏨 Hotel API corriendo en http://localhost:3001
✅ Conectado a MySQL/MariaDB

Lista de patrones aplicados en este proyecto:

Factory Method
Se utiliza para crear distintos tipos de habitaciones (Standard, Suite y Premium) sin depender de clases concretas. Esto permite agregar nuevos tipos de habitaciones en el futuro sin modificar el código existente.

Builder
Se emplea para construir objetos complejos como una reserva, permitiendo configurar paso a paso sus diferentes atributos, como huésped, habitación, fechas, servicios adicionales y promociones.

Decorator
Se utiliza para añadir servicios adicionales a una estadía de manera dinámica, como desayuno, spa o cochera, sin modificar la estructura de las clases base.

Facade
Se implementa para proporcionar una interfaz simplificada a los distintos subsistemas del hotel, facilitando operaciones complejas como la gestión de reservas, habitaciones y facturación.

Strategy
Se utiliza para encapsular diferentes algoritmos de cálculo de precios, permitiendo aplicar distintas políticas según promociones, temporadas o categorías de huéspedes.
State
Se emplea para modelar los distintos estados de una reserva y de una habitación, permitiendo que su comportamiento cambie según el estado actual en el que se encuentren.

Observer
Se utiliza para implementar un sistema de notificaciones, permitiendo informar automáticamente a los huéspedes cuando ocurren cambios relevantes en sus reservas o habitaciones.

Mediator
Se emplea para centralizar la comunicación entre los diferentes componentes del sistema, reduciendo el acoplamiento entre reservas, habitaciones, huéspedes y otros elementos del dominio.

Template Method
Se utiliza para definir el flujo general de procesos que siguen una estructura común, permitiendo que algunas etapas sean personalizadas por las subclases. En el sistema hotelero puede aplicarse, por ejemplo, al proceso de cálculo de facturación o gestión de estadías, donde existen pasos comunes pero ciertas variaciones según el tipo de habitación, promoción o categoría de huésped.

Principios SOLID:

S — Single Responsibility Principle (Principio de Responsabilidad Única)
Una clase debe tener una sola razón para cambiar, enfocándose en una única tarea o comportamiento.
Aplicación en tu sistema: Separamos la lógica de cálculo financiero en EstrategiaPrecio y Factura, evitando que la clase Reserva o Estadía se encargue de sumar costos, aplicar impuestos o calcular descuentos (RF15, RF16).
La clase Notificación (o un servicio Notifier) se encarga exclusivamente del formateo y envío de alertas (RF17, RF18, RF19), liberando a Reserva o ListaEspera de saber cómo se envía un correo o un SMS.

O — Open/Closed Principle (Principio de Abierto/Cerrado)
 Las entidades de software deben estar abiertas para su extensión, pero cerradas para su modificación.
Aplicación en tu sistema (Crucial para cumplir el RNF01):
Estrategias de Precio: Al usar el patrón Strategy (EstrategiaPrecio), si el hotel decide crear un nuevo tipo de descuento por "Temporada Súper Alta" o "Convenio Corporativo", no modificas el código existente; creas una nueva clase que herede de la interfaz de estrategias.
Servicios Adicionales: El uso de Decorator o una estructura polimórfica para ServicioExtra (RF12) permite añadir masajes, excursiones o cocheras sin alterar la clase Estadía.

L — Liskov Substitution Principle (Principio de Sustitución de Liskov)
Los objetos de una subclase deben poder reemplazar a los objetos de la superclase sin alterar el correcto funcionamiento del programa.
Aplicación en tu sistema:
Al implementar las variantes de EstrategiaPrecio (DescuentoVIP, DescuentoFrecuente, PrecioEstándar), el componente encargado de facturar puede invocar el método .calcularMonto() de manera uniforme, sin necesidad de preguntar con un if/else el tipo exacto de objeto que está procesando.

I — Interface Segregation Principle (Principio de Segregación de Interfaces)
Una clase no debe ser forzada a depender de interfaces o métodos que no utiliza. Es mejor tener interfaces pequeñas y específicas.
Aplicación en tu sistema:
El sistema interactúa con distintos actores (Huésped, Recepcionista, Administrador). Las interfaces de los servicios de la aplicación deben estar segregadas. Por ejemplo, una interfaz IReservaConsulta (para que el Huésped vea su estado) debe estar separada de IReservaGestion (usada por el Recepcionista para hacer Check-in/Check-out o cancelaciones), evitando que el Huésped acceda a métodos de administración.

D — Dependency Inversion Principle (Principio de Inversión de Dependencias)
Los módulos de alto nivel no deben depender de módulos de bajo nivel; ambos deben depender de abstracciones.
Aplicación en tu sistema:
Notificaciones (RF17): El core del negocio (Reserva) no depende de una clase concreta EmailSender. Depende de una interfaz abstracta INotificador. Esto permite que, si en el futuro se cambia de un servicio de Email a WhatsApp o notificaciones Push, la lógica de la reserva permanezca intacta.
Cumple directamente con el RNF01, que exige explícitamente diseñar con interfaces y no con implementaciones concretas.

Patrones GRASP:

Experto en Información (Information Expert)
Asignar una responsabilidad a la clase que cuenta con la información necesaria para llevarla a cabo.
Aplicación en tu sistema:
Habitacion es la responsable de saber si está disponible o no en base a su propio EstadoHabitacion e historial.
Factura o Estadía es la Experta en Información para consolidar el precio final, ya que conoce qué ServiciosExtras se consumieron y qué Promocion está activa para ese período.

Creador (Creator)
Ayuda a decidir qué clase debe encargarse de instanciar o crear un nuevo objeto.
Aplicación en tu sistema:
Hotel como creador: La clase Hotel contiene/agrega a las Habitaciones y a los Huéspedes. Por ende, es la candidata natural para registrar (crear) nuevas instancias de estos.
Factory Method (mencionado en tus notas): Decidiste delegar la creación compleja de las habitaciones a una fábrica especializada (HabitacionFactory), separando las complejidades de instanciación según su tipología (Suite, Estándar, etc.).

Controlador (Controller)
Asignar la responsabilidad de recibir y coordinar las operaciones del sistema desde la interfaz de usuario hacia las capas internas del negocio.
Aplicación en tu sistema:
Mencionaste la posibilidad de un Facade (Check-in y Check-out). Esta fachada actúa como un Controlador de Fachada (Facade Controller), recibiendo los eventos de los Casos de Uso del Recepcionista (CU08 y CU09) y coordinando las acciones internas: cambiar el estado de la habitación, activar la estadía y disparar la orden de facturación.

Bajo Acoplamiento (Low Coupling)
Diseñar las clases de manera que las dependencias entre ellas sean mínimas, aumentando la reusabilidad y disminuyendo el impacto ante cambios.
Aplicación en tu sistema (Alineado con RNF02):
Al implementar el patrón Observer para la Lista de Espera, la clase Reserva no conoce la existencia exacta de la lista de espera ni sabe a quién notificar. Solo emite un evento de "Habitación Liberada", y el componente de ListaEspera reacciona. Esto evita que Reserva esté acoplada a la gestión de las colas de espera.

Alta Cohesión (High Cohesion)
Mantener las responsabilidades de una clase estrechamente relacionadas y enfocadas.
Aplicación en tu sistema:
Cada clase candidata tiene un alcance muy limpio y acotado. Notificacion solo maneja alertas, Reserva solo maneja fechas y estados temporales, y EstrategiaPrecio solo maneja algoritmos matemáticos de costos. No hay clases "monstruo" o "todopoderosas" que realicen tareas dispares.

Polimorfismo (Polymorphism)
Asignar la responsabilidad del comportamiento variante a los tipos para los cuales el comportamiento varía, utilizando operaciones polimórficas.
Aplicación en tu sistema:
El cálculo de descuentos según la categoría del huésped (Estándar, Frecuente, VIP) se resuelve mediante polimorfismo gracias al patrón Strategy, eliminando los bloques condicionales complejos (switch o if) basados en atributos de tipo.

Fabricación Pura (Pure Fabrication)
Crear una clase artificial que no representa un concepto del dominio del problema, pero que ayuda a lograr bajo acoplamiento y alta cohesión.
Aplicación en tu sistema:
Las clases EstrategiaPrecio, Builder (para Reserva) y las Fábricas de habitaciones no existen en el "mundo real" de un hotel físico (un conserje no ve una "estrategia" flotando). Son fabricaciones puras del diseño de software creadas para estructurar de forma limpia el cálculo y la construcción de objetos.

Indirección (Indirection)
Asignar la responsabilidad a un objeto intermedio para mediar entre otros componentes, evitando el acoplamiento directo.
Aplicación en tu sistema:
El uso de los patrones Adapter (mencionado para integrar servicios externos, como pasarelas de pago o sistemas de clima) actúa como una indirección. El sistema del hotel no habla directamente con la API de Visa o PayPal; habla con un adaptador propio que traduce las peticiones.

Distribución de tareas:
Agos lo hace dsp

