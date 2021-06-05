# Annotation on spring
## Pointers
* Spring manages a group of objects called `beans` for dependency injection.
* `@Bean` annotation is a method level, which generates Object.
* `@Component` annotation is a class level, it creates a bean object for the class.
* `@Autowired` annotation is variable level, Spring will do dependency injection of the variable based on the type of variable.
* `@Autowired` annotation doesn't work with static variables and non-component class.
* When multiple bean is having same type, `@Primary` is used with bean to give priority.
* We can explicitly define the dependency by `@Qualifier(dependency_name)`.