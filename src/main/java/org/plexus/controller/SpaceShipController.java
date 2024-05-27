package org.plexus.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.plexus.dto.SpaceShipDTO;
import org.plexus.service.SpaceShipService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/spaceShips")
public class SpaceShipController {

	private final SpaceShipService spaceShipService;

	@GetMapping
	public ResponseEntity<List<SpaceShipDTO>> getAll() {
		return ResponseEntity.ok(spaceShipService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<SpaceShipDTO> get(@PathVariable Integer id) {
		return ResponseEntity.ok(spaceShipService.get(id));
	}

	@GetMapping("/searchByNameContaining/{name}")
	public ResponseEntity<List<SpaceShipDTO>> searchByNameContaining(@PathVariable String name) {
		return ResponseEntity.ok(spaceShipService.searchByNameContaining(name));
	}

	@PostMapping
	public ResponseEntity<SpaceShipDTO> createSpaceShip(@RequestBody @Valid SpaceShipDTO spaceShipDTO) {
		return ResponseEntity.ok(spaceShipService.create(spaceShipDTO));
	}

	@PutMapping("/{id}")
	public ResponseEntity<SpaceShipDTO> updateSpaceShip(@PathVariable Integer id, @RequestBody @Valid SpaceShipDTO updateShapeShipDTO) {
		return ResponseEntity.ok(spaceShipService.update(id,updateShapeShipDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> deleteSpaceShip(@PathVariable Integer id) {
		return ResponseEntity.ok(spaceShipService.delete(id));
	}

}
