# Simple Web Stack 2
This is a Jersey 2 version of [teamlazerbees/simple-web-stack][sws1].
First and foremost, the objective of this project is to upgrade to Jersey 2, while still using Guice.

## Jersey 2 and Guice
Jersey 2 uses HK2 as its intrinsically hardwired DI framework. Out of the box, HK2 is not entirely usable with Guice.
This project utilizes a unidirectional `org.glassfish.hk2:guice-bridge` to make classes that are managed by Guice available to HK2 within Jersey 2.

### Relying on Guice Modules Only
Often, you'll find that your Jersey types (like resources or providers) are defined nowhere near where your code/config for letting Jersey know about them lives.
In this project, all Endpoints live in the `sws-web-api` module, while the Jersey configuration lives in the `sws2-webapp` module.
Normally this would require developers to keep the set of available Jersey types in sync with this Jerset configuration, but in this example project this is done with the help of Guice's Module trees.
If the majority of all classes is going to be managed by Guice, the Jersey types might as well be managed in the same way.

### Unidirectional HK2 guice-bridge
The use of the `guice-bridge` library in this projects makes Guice-managed types availabe to Jersey (and HK2 within it).
Since this binding is unidirectional, this means that as far as Jersey types go, Jersey is going to be the runtime entry point to your application: I.e. Jersey will be in charge of managing Jersey type instances' lifecycles, while Guice may merely be assisting in that.

In this project, you'll find the `GreetingEndpoint` to be an example of that:
The resource class's lifecycle is managed by jersey, while all of its non-`@Context` injected dependencies (i.e. `GreetingService`) are managed by Guice.

This setup offers no guidance for managing the injection of HK2-managed dependencies into other HK2-managed types, or managing the injection of HK2-managed dependencies into Guice-managed types.

## Application Startup Sequence
Using a very minimal `web.xml` in the `sws2-webapp` module, the application stars up using a `Sws2ServletContextListener`. Here the Guice `Injector` is being constructed, and a tree of connected `Module`s is loaded.
The Jersey configuration is loaded next, by means of the `web.xml`-specified `JerseyApplication`.
This `JerseyApplication` obtains the Guice `Injector` which was previously created, and looks up all `Class` instances that were previously bound to the `Injector`.
These `Class` instances are then registered with Jersey (and its HK2 DI container).

In order to register a Jersey type with Guice to be passed along to Jersey, implementors should make use of the `JerseyTypesModule`, which wraps a Guice multibinder to bind all these types under the same `Key`.
Ultimately this leads to a `Set<Class<?>>` for all explicitly Gucie-bound types that is being passed to `JerseyApplication`.

[sws1]: https://github.com/teamlazerbeez/simple-web-stack