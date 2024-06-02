package org.plexus.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.plexus.dto.InputSpaceShipDTO;
import org.plexus.dto.SpaceShipDTO;
import org.plexus.service.SpaceShipService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/space-ship")
public class SpaceShipController {

	private final SpaceShipService spaceShipService;


	@GetMapping("/{id}")
	public ResponseEntity<SpaceShipDTO> get(@PathVariable Integer id) {
		return ResponseEntity.ok(spaceShipService.get(id));
	}

	@PostMapping
	public ResponseEntity<SpaceShipDTO> createSpaceShip(@RequestBody @Valid InputSpaceShipDTO inputSpaceShipDTO) {
		return ResponseEntity.ok(spaceShipService.create(inputSpaceShipDTO));
	}

	@PutMapping("/{id}")
	public ResponseEntity<SpaceShipDTO> updateSpaceShip(@PathVariable Integer id, @RequestBody InputSpaceShipDTO inputSpaceShipDTO) {
		return ResponseEntity.ok(spaceShipService.update(id,inputSpaceShipDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> deleteSpaceShip(@PathVariable Integer id) {
		return ResponseEntity.ok(spaceShipService.delete(id));
	}

	@GetMapping("/")
	public Page<SpaceShipDTO> getAllSpaceShips(
			@RequestParam int page,
			@RequestParam int size) {
		return spaceShipService.getAllSpaceShips(page, size);
	}

	@GetMapping
	public ResponseEntity<List<SpaceShipDTO>> getAll() {
		return ResponseEntity.ok(spaceShipService.getAll());
	}


	@GetMapping("/searchByNameContaining")
	public ResponseEntity<List<SpaceShipDTO>> searchByNameContaining(@RequestParam String name) {
		return ResponseEntity.ok(spaceShipService.searchByNameContaining(name));
	}

}
