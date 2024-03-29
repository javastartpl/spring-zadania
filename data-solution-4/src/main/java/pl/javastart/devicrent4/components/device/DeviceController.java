package pl.javastart.devicrent4.components.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.devicrent4.components.category.Category;
import pl.javastart.devicrent4.components.category.CategoryRepository;

import jakarta.transaction.Transactional;
import java.util.Optional;
import java.util.Scanner;

@Service
public class DeviceController {

    private final Scanner scanner;
    private final DeviceRepository deviceRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public DeviceController(Scanner scanner, DeviceRepository deviceRepository, CategoryRepository categoryRepository) {
        this.scanner = scanner;
        this.deviceRepository = deviceRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void createDevice() {
        try {
            Device device = readDevice();
            deviceRepository.save(device);
            System.out.println("Dodano urządzenie");
            System.out.println(device);
        } catch (CategoryNotFoundException e) {
            System.err.println("Urządzenia nie dodano. " + e.getMessage());
        }
    }

    private Device readDevice() {
        Device device = new Device();
        System.out.println("Nazwa urządzenia:");
        device.setName(scanner.nextLine());
        System.out.println("Opis urządzenia:");
        device.setDescription(scanner.nextLine());
        System.out.println("Cena urządzenia:");
        device.setPrice(scanner.nextDouble());
        System.out.println("Ilość(szt) urządzenia:");
        device.setQuantity(scanner.nextInt());
        System.out.println("Kategoria(id) urządzenia:");
        long categoryId = scanner.nextLong();
        Optional<Category> category = categoryRepository.findById(categoryId);
        scanner.nextLine();
        category.ifPresentOrElse(device::setCategory,
                () -> {
                    throw new CategoryNotFoundException("Kategoria o podanym ID nie istnieje");
                }
        );
        return device;
    }

    public void removeDevice() {
        System.out.println("Podaj id urządzenia, które chcesz usunąć:");
        long deviceId = scanner.nextLong();
        Optional<Device> device = deviceRepository.findById(deviceId);
        device.ifPresentOrElse(deviceRepository::delete, () -> System.out.println("Brak urządzenia o wskazanym ID"));
    }
}
