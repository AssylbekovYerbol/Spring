package com.example.demo.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookResponse> findAll() {
        return bookRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public Page<BookResponse> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).map(this::toResponse);
    }

    public BookResponse findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        return toResponse(book);
    }

    public BookResponse create(CreateBookRequest request) {
        Book book = new Book(request.getTitle(), request.getAuthor(),
                request.getIsbn(), request.getPublicationYear());
        Book saved = bookRepository.save(book);
        return toResponse(saved);
    }

    public BookResponse update(Long id, UpdateBookRequest request) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        existing.setTitle(request.getTitle());
        existing.setAuthor(request.getAuthor());
        existing.setIsbn(request.getIsbn());
        existing.setPublicationYear(request.getPublicationYear());
        Book saved = bookRepository.save(existing);
        return toResponse(saved);
    }

    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
    }

    private BookResponse toResponse(Book book) {
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(),
                book.getIsbn(), book.getPublicationYear());
    }

    public Page<BookResponse> searchByTitle(String title, Pageable pageable) {
        return bookRepository.findByTitleContainingIgnoreCase(title, pageable)
                .map(this::toResponse);
    }

}