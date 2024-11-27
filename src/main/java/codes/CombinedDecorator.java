package codes;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;

public class CombinedDecorator extends DefaultFieldDecorator {

    public CombinedDecorator(SearchContext context) {
        super(new DefaultElementLocatorFactory(context));
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<?> decoratableClass = getDecoratableClass(field);
        if (decoratableClass != null) {
            ElementLocator locator = factory.createLocator(field);
            if (locator == null) {
                return null;
            }
            return createCustomElement(loader, locator, decoratableClass);
        }
        return super.decorate(loader, field);
    }

    private <T> T createCustomElement(ClassLoader loader, ElementLocator locator, Class<T> clazz) {
        WebElement proxy = proxyForLocator(loader, locator);
        try {
            return clazz.getConstructor(WebElement.class).newInstance(proxy);
        } catch (Exception e) {
            throw new AssertionError("Failed to create instance of " + clazz.getName() + " with WebElement", e);
        }
    }

    private Class<?> getDecoratableClass(Field field) {
        Class<?> clazz = field.getType();
        try {
            clazz.getConstructor(WebElement.class);
        } catch (Exception e) {
            return null;
        }
        return clazz;
    }
}
