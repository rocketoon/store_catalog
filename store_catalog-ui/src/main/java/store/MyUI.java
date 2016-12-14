package store;

import java.math.BigDecimal;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.Converter.ConversionException;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import store.backend.CrudService;
import store.model.Product;

/**
 *
 */
@Theme("mytheme")
public class MyUI extends UI {

	private CrudService<Product> service = new CrudService<>();
	private BeanItemContainer<Product> dataSource = new BeanItemContainer<Product>(Product.class);

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		final VerticalLayout layout = new VerticalLayout();
		final TextField name = new TextField();
		name.setCaption("Type name here:");
		final TextField price = new TextField();
		price.setConverter(BigDecimal.class);
		price.setCaption("Type price here:");
		final TextField amount = new TextField();
		amount.setCaption("Type amount here:");
		amount.setConverter(Integer.class);

		Button button = new Button("Click Me");
		button.addClickListener(e -> {
			String prodPrice = price.getValue();
			String prodAmount = amount.getValue();
			BigDecimal convertedPrice = null;
			Integer convertedAmount = null;
			try {
				convertedPrice = (BigDecimal) price.getConvertedValue();
				Notification
						.show("UI value (String): " + prodPrice + "<br />Converted value (Long): " + convertedPrice);
			} catch (ConversionException ex1) {
				Notification.show("Could not convert value: " + prodPrice);
			}
			try {
				convertedAmount = (Integer) amount.getConvertedValue();
				Notification
						.show("UI value (String): " + prodAmount + "<br />Converted value (Long): " + convertedAmount);
			} catch (ConversionException ex1) {
				Notification.show("Could not convert value: " + prodAmount);
			}
			if (convertedPrice != null && convertedAmount != null) {
				service.save(new Product(name.getValue(), convertedPrice, convertedAmount));
				dataSource.removeAllItems();
				dataSource.addAll(service.findAll());
			}
		});

		Grid grid = new Grid(dataSource);
		grid.setSizeFull();

		// This is a component from the store_catalog-addon module
		// layout.addComponent(new MyComponent());
		layout.addComponents(name, price, amount, button, grid);
		layout.setSizeFull();
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setExpandRatio(grid, 1.0f);

		setContent(layout);
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
