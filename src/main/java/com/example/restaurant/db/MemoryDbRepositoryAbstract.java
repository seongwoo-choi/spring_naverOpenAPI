package com.example.restaurant.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// MemoryDbRepositoryAbstract 형태는 MemoryDbRepositoryIfs<T> 를 상속받는 형태이고 인터페이스를 받아오는 것이기 때문에 메서드를 마저 작성해줘야 한다.
// 와일드카드로써 MemoryDbEntity 를 사용 => T 는 MemoryDbEntity 를 상속한 어떤 것!
// 즉 전체적인 구조는 MemoryDbRepositoryIfs 인터페이스 + MemoryDbEntity 클래스 를 합친 구조이다.
abstract public class MemoryDbRepositoryAbstract<T extends MemoryDbEntity> implements MemoryDbRepositoryIfs<T> {

    // 배열을 데이터베이스 역할을 하게 하였다.
    private final List<T> db = new ArrayList<>();
    private int index = 0;

    @Override
    public Optional<T> findById(int index) {
        // getIndex() ==> MemoryDbEntity 의 index 게터 함수이다.
        // stram() 을 사용하여 배열을 순차적으로 순회하고 filter() 를 사용하여 입력받은 index 의 값과 db 배열에 존재하는 index 의 값과 비교하여 같으면
        // 첫번째 값을 가져온다.
        return db.stream().filter(it -> it.getIndex() == index).findFirst();
    }

    @Override
    public void deleteById(int index) {
        var optionalEntity = db.stream().filter(it -> it.getIndex() == index).findFirst();
        if (optionalEntity.isPresent()) {
            db.remove(optionalEntity.get());
        }
    }

    @Override
    public List<T> listAll() {
        return db;
    }

    @Override
    public T save(T entity) {
        // db 배열에서 index 를 찾아서 저장하고자 하는 entity 의 index 와 비교하여 같으면 그 첫번째 값을 저장
        var optionalEntity = db.stream().filter(it -> it.getIndex() == entity.getIndex()).findFirst();

        // db 에 데이터가 없는 경우
        if (optionalEntity.isEmpty()) {
            // pk => 1씩 자동으로 increase 비슷하게 구현
            index++;
            entity.setIndex(index);

            // DB 에 저장하고자 하는 데이터, 입력받은 entity 를 db 배열에 저장
            db.add(entity);
            return entity;
        }
        // db 에 데이터가 있는 경우
        else {
            // optionalEntity.get() => optionalEntity 객체를 가져온다.
            var preIndex = optionalEntity.get().getIndex();
            entity.setIndex(preIndex);

            // db 에 데이터를 덮어씌운다.
            deleteById(preIndex);
            db.add(entity);
            return entity;
        }
    }
}
