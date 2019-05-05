package farinman.ba.pipe_brake_process.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import farinman.ba.pipe_brake_process.entities.Device;

public interface DeviceRepository extends JpaRepository<Device, String> {

}
