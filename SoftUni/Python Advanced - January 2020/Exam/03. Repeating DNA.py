def get_repeating_DNA(dna):
    results = list()
    final = []
    
    for i in range(len(dna) - 9):
        results.append(dna[i:i + 10])
    for j in results:
        if results.count(j) > 1 and j not in final:
            final.append(j)
    
    return final
